/*
 * File Name: QuantityInfo.java
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
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-10-21
 * @see       
 * @since     DSPlite V0.2.0
 */

public class QuantityInfo {
	
	private String quantityId;
	private String appKey;
	private String appName;
	private String apiMethod;
	private int total;
	private int used;
	private Date creationDate;
	
	
	/**
	 * @return the quantityId
	 */
	public String getQuantityId() {
		return quantityId;
	}
	/**
	 * @param quantityId the quantityId to set
	 */
	public void setQuantityId(String quantityId) {
		this.quantityId = quantityId;
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
		this.appKey = appKey;
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
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @return the used
	 */
	public int getUsed() {
		return used;
	}
	/**
	 * @param used the used to set
	 */
	public void setUsed(int used) {
		this.used = used;
	}
	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}
	/**
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	/**
	 * 〈一句话功能简述〉
	 * 
	 * @see java.lang.Object#toString()
	 * @return
	 */
	@Override
	public String toString() {
		return "QuantityInfo [quantityId=" + quantityId + ", appKey=" + appKey
				+ ", apiMethod=" + apiMethod + ", total=" + total + ", used="
				+ used + ", creationDate=" + creationDate + "]";
	}
	
	public Map<String, Object> toMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appName", this.appName);
		map.put("apiMethod", this.apiMethod);
		map.put("total", this.total);
		map.put("used", this.used);
		map.put("creationDate", this.creationDate);
		return map;
	}
	
}
