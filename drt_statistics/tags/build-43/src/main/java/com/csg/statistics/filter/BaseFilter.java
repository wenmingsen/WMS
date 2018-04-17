package com.csg.statistics.filter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseFilter {
	/**
	 * 输出text数据
	 * @param response
	 * @param obj
	 * @throws java.io.IOException
	 * @throws java.io.IOException
	 */
	protected void writeText(HttpServletResponse response, String obj) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(obj);
		response.getWriter().close();
	}
}
