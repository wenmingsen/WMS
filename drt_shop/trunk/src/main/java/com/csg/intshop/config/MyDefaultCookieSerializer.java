package com.csg.intshop.config;

import org.springframework.session.web.http.CookieSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyDefaultCookieSerializer implements CookieSerializer {

	private String cookieName = "SESSION";
	private Boolean useSecureCookie;
	private boolean useHttpOnlyCookie = isServlet3();
	private String cookiePath;
	private int cookieMaxAge = -1;
	private String domainName;
	private Pattern domainNamePattern;
	private String jvmRoute;
	private boolean useBase64Encoding;
	private String rememberMeRequestAttribute;

	public List<String> readCookieValues(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		List<String> matchingCookieValues = new ArrayList<>();
		if (cookies != null) {
			for (Cookie cookie : cookies){
				System.out.print(cookie.getName()+":"+cookie.getPath());
				if (this.cookieName.equals(cookie.getName())) {
					String sessionId = this.useBase64Encoding ? base64Decode(cookie
							.getValue()) : cookie.getValue();
					if (sessionId != null) {
						if ((this.jvmRoute != null)
								&& (sessionId.endsWith(this.jvmRoute))) {
							sessionId = sessionId
									.substring(0, sessionId.length()
											- this.jvmRoute.length());
						}
						matchingCookieValues.add(sessionId);
					}
				}
			}
		}
		return matchingCookieValues;
	}

	public void writeCookieValue(CookieSerializer.CookieValue cookieValue) {
		HttpServletRequest request = cookieValue.getRequest();
		HttpServletResponse response = cookieValue.getResponse();

		String requestedCookieValue = cookieValue.getCookieValue();
		String actualCookieValue = requestedCookieValue + (this.jvmRoute==null?"":this.jvmRoute);

		Cookie sessionCookie = new Cookie(this.cookieName,
				this.useBase64Encoding ? base64Encode(actualCookieValue)
						: actualCookieValue);
		sessionCookie.setSecure(isSecureCookie(request));
		sessionCookie.setPath(getCookiePath(request));
		String domainName = getDomainName(request);
		if (domainName != null) {
			sessionCookie.setDomain(domainName);
		}

		if (this.useHttpOnlyCookie) {
			sessionCookie.setHttpOnly(true);
		}

		if ("".equals(requestedCookieValue)) {
			sessionCookie.setMaxAge(0);
		} else if ((this.rememberMeRequestAttribute != null)
				&& (request.getAttribute(this.rememberMeRequestAttribute) != null)) {
			sessionCookie.setMaxAge(2147483647);
		} else {
			sessionCookie.setMaxAge(this.cookieMaxAge);
		}

		response.addCookie(sessionCookie);
	}

	private String base64Decode(String base64Value) {
		try {
			byte[] decodedCookieBytes = Base64.decode(base64Value.getBytes());
			return new String(decodedCookieBytes);
		} catch (Exception e) {
		}
		return null;
	}

	private String base64Encode(String value) {
		byte[] encodedCookieBytes = Base64.encode(value.getBytes());
		return new String(encodedCookieBytes);
	}

	public void setUseSecureCookie(boolean useSecureCookie) {
		this.useSecureCookie = Boolean.valueOf(useSecureCookie);
	}

	public void setUseHttpOnlyCookie(boolean useHttpOnlyCookie) {
		if ((useHttpOnlyCookie) && (!isServlet3())) {
			throw new IllegalArgumentException(
					"You cannot set useHttpOnlyCookie to true in pre Servlet 3 environment");
		}

		this.useHttpOnlyCookie = useHttpOnlyCookie;
	}

	private boolean isSecureCookie(HttpServletRequest request) {
		if (this.useSecureCookie == null) {
			return request.isSecure();
		}
		return this.useSecureCookie.booleanValue();
	}

	public void setCookiePath(String cookiePath) {
		this.cookiePath = cookiePath;
	}

	public void setCookieName(String cookieName) {
		if (cookieName == null) {
			throw new IllegalArgumentException("cookieName cannot be null");
		}
		this.cookieName = cookieName;
	}

	public void setCookieMaxAge(int cookieMaxAge) {
		this.cookieMaxAge = cookieMaxAge;
	}

	public void setDomainName(String domainName) {
		if (this.domainNamePattern != null) {
			throw new IllegalStateException(
					"Cannot set both domainName and domainNamePattern");
		}

		this.domainName = domainName;
	}

	public void setDomainNamePattern(String domainNamePattern) {
		if (this.domainName != null) {
			throw new IllegalStateException(
					"Cannot set both domainName and domainNamePattern");
		}

		this.domainNamePattern = Pattern.compile(domainNamePattern, 2);
	}

	public void setJvmRoute(String jvmRoute) {
		this.jvmRoute = ("." + jvmRoute);
	}

	public void setUseBase64Encoding(boolean useBase64Encoding) {
		this.useBase64Encoding = useBase64Encoding;
	}

	public void setRememberMeRequestAttribute(String rememberMeRequestAttribute) {
		if (rememberMeRequestAttribute == null) {
			throw new IllegalArgumentException(
					"rememberMeRequestAttribute cannot be null");
		}

		this.rememberMeRequestAttribute = rememberMeRequestAttribute;
	}

	private String getDomainName(HttpServletRequest request) {
		if (this.domainName != null) {
			return this.domainName;
		}
		if (this.domainNamePattern != null) {
			Matcher matcher = this.domainNamePattern.matcher(request
					.getServerName());
			if (matcher.matches()) {
				return matcher.group(1);
			}
		}
		return null;
	}

	private String getCookiePath(HttpServletRequest request) {
		if (this.cookiePath == null) {
			return  "/";
		}
		return this.cookiePath;
	}

	private boolean isServlet3() {
		try {
			ServletRequest.class.getMethod("startAsync", new Class[0]);
			return true;
		} catch (NoSuchMethodException localNoSuchMethodException) {
		}
		return false;
	}

}
