/*
 * File Name: AuditTrail.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-10-21

 * Modifier: Wuwuhao
 * Modify Date: 2016-10-21
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.model;

import java.util.Date;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-10-21
 * @see       
 * @since     DSPlite V0.2.0
 */

public class AuditTrail {
	
	public final static String DEFAULT_NULL_KEY = "null_key";
	
	private String auditId;
	private String appKey=DEFAULT_NULL_KEY;
	private String apiMethod;
	private int    apiType;
	private String parameters;
	private Date   auditDate;
	
	/**
	 * @return the auditId
	 */
	public String getAuditId() {
		return auditId;
	}
	/**
	 * @param auditId the auditId to set
	 */
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}
	/**
	 * @return the appKey
	 */
	public String getAppKey() {
		return appKey;
	}
	/**
	 * @param appKey the appKey to set
	 */
	public void setAppKey(String appKey) {
		this.appKey = null==appKey?DEFAULT_NULL_KEY:appKey;
	}
	/**
	 * @return the apiMethod
	 */
	public String getApiMethod() {
		return apiMethod;
	}
	/**
	 * @param apiMethod the apiMethod to set
	 */
	public void setApiMethod(String apiMethod) {
		this.apiMethod = apiMethod;
	}
	/**
	 * @return the apiType
	 */
	public int getApiType() {
		return apiType;
	}
	/**
	 * @param apiType the apiType to set
	 */
	public void setApiType(int apiType) {
		this.apiType = apiType;
	}
	/**
	 * @return the parameters
	 */
	public String getParameters() {
		return parameters;
	}
	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	/**
	 * @return the auditDate
	 */
	public Date getAuditDate() {
		return auditDate;
	}
	/**
	 * @param auditDate the auditDate to set
	 */
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	/**
	 * 〈一句话功能简述〉
	 * 
	 * @see java.lang.Object#toString()
	 * @return
	 */
	@Override
	public String toString() {
		return "AuditTrail [auditId=" + auditId + ", appKey=" + appKey
				+ ", apiMethod=" + apiMethod + ", apiType=" + apiType
				+ ", parameters=" + parameters + ", auditDate=" + auditDate
				+ "]";
	}
	
}
