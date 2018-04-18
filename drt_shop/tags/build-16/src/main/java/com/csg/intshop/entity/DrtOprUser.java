/*
 * Copyright 2012 DrtOpr, Inc. All rights reserved.
 * Website: http://www.DrtOpr.com
 */
 package com.csg.intshop.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

/**
 * @author DrtOpr
 * @version 1.0
 * @since 1.0
 */
public class DrtOprUser implements UserDetails, Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 6185841431625671589L;
	private transient Collection<GrantedAuthority> grantedAuths;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuths;
    }

    @Override
    public String getUsername() {
        return this.acount;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    /**
	 * 
	 */

	//columns START
	private String userId;
	private String name;
	private String acount;
	private String password;
	private String description;
	private String isDelete;
	private Timestamp createTime;
	private BigDecimal loginFailCnt;
	private Timestamp loginFailTime;
	private String parentId;
	private String num;
	private String userType;
	//private boolean systemUser;
	private String isSystemUser;
	private String department;
	private String post;
	private String sex;
	private String phone;
	private String email;
	
	private List<String> userRoles;

    //columns END

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcount() {
        return acount;
    }

    public void setAcount(String acount) {
        this.acount = acount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getLoginFailCnt() {
        return loginFailCnt;
    }

    public void setLoginFailCnt(BigDecimal loginFailCnt) {
        this.loginFailCnt = loginFailCnt;
    }

    public Timestamp getLoginFailTime() {
        return loginFailTime;
    }

    public void setLoginFailTime(Timestamp loginFailTime) {
        this.loginFailTime = loginFailTime;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getIsSystemUser() {
        return isSystemUser;
    }

    public void setIsSystemUser(String isSystemUser) {
        this.isSystemUser = isSystemUser;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public List<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<String> userRoles) {
		this.userRoles = userRoles;
	}

	public void setSpringSecurityProp(boolean enables,
                                      boolean accountNonExpired, boolean credentialsNonExpired,
                                      boolean accountNonLocked, Collection<GrantedAuthority> grantedAuths) {
        this.enables = enables;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.grantedAuths = grantedAuths;
    }

    // 实现spring security 3 UserDetails接口属性
    private boolean enables;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
}

