/*
 * File Name: APIinfo.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-8-28

 * Modifier: Wuwuhao
 * Modify Date: 2016-8-28
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;

import com.cetiti.dsp.soap.CodeGenerator;
import com.cetiti.dsp.util.IpPortUtil;
import com.cetiti.dsp.util.SqlSessionFactoryRepos;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-8-28
 * @see       
 * @since     DSPlite V0.2.0
 */

public class APIinfo implements Cloneable{
	
	private String apiName;
	private String soapApiName;
	private String restApiName;
	private String selectId;
	private String nameSpace;
	private String fullQualifiedName;
	private transient SqlSessionFactory sqlSessionFactory;
	private String sqlText;
	private String sqlType;
	private List<String> parameters;
	private String dataSource;
	private String metadataFile;
	
	private static String baseRestPath;

	
	
	@Override
	public APIinfo clone() throws CloneNotSupportedException {  
	    APIinfo apIinfo =  (APIinfo) super.clone();
	    return apIinfo;
	}
	/**
	 * @return the apiName
	 */
	public String getApiName() {
		return apiName;
	}
	/**
	 * @param apiName the apiName to set
	 */
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	/**
	 * @return the soapApiName
	 */
	public String getSoapApiName() {
		return soapApiName;
	}
	/**
	 * @param soapApiName the soapApiName to set
	 */
	public void buildSoapApiName() {
	    String[] APIs = this.apiName.split("/");
		this.soapApiName = IpPortUtil.getSoapServer()+
			CodeGenerator.getPackage(APIs[0])+"/"+
			CodeGenerator.getSimpleClassName(APIs[0])+"?wsdl";
	}
	/**
	 * @return the restApiName
	 */
	public String getRestApiName() {
		return restApiName;
	}
	/**
	 * @param restApiName the restApiName to set
	 */
	public void buildRestApiName(String basePath) {
		this.restApiName = basePath + this.apiName;
	}
	
	public void buildRestApiName() {
		this.restApiName = baseRestPath + this.apiName;
	}
	public static void setBaseRestPath(String baseRestPath) {
		APIinfo.baseRestPath = baseRestPath;
	}
	/**
	 * @return the selectId
	 */
	public String getSelectId() {
		return selectId;
	}
	/**
	 * @param selectId the selectId to set
	 */
	public void setSelectId(String selectId) {
		this.selectId = selectId;
	}
	/**
	 * @return the nameSpace
	 */
	public String getNameSpace() {
		return nameSpace;
	}
	/**
	 * @param nameSpace the nameSpace to set
	 */
	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
	/**
	 * @return the fullQualifiedName
	 */
	public String getFullQualifiedName() {
		return fullQualifiedName;
	}
	/**
	 * @param fullQualifiedName the fullQualifiedName to set
	 */
	public void setFullQualifiedName(String fullQualifiedName) {
		this.fullQualifiedName = fullQualifiedName;
	}
	/**
	 * @return the sqlSessionFactory
	 */
	public SqlSessionFactory getSqlSessionFactory() {
		if(null!=dataSource){
			SqlSessionFactory ssf = SqlSessionFactoryRepos.getSessionFactory(dataSource);
			if(null!=ssf){
				return ssf;
			}
		}
		
		return sqlSessionFactory;
	}
	
	/**
	 * @return the sqlText
	 */
	public String getSqlText() {
		return sqlText;
	}
	/**
	 * @param sqlText the sqlText to set
	 */
	public void setSqlText(String sqlText) {
		this.sqlText = null!=sqlText?sqlText.replaceAll("\\s+", " "):null;
	}
	/**
	 * @return the sqlType
	 */
	public String getSqlType() {
		return sqlType;
	}
	/**
	 * @param sqlType the sqlType to set
	 */
	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}
	/**
	 * @return the parameters
	 */
	public List<String> getParameters() {
		return parameters;
	}
	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}
	/**
	 * @return the dataSource
	 */
	public String getDataSource() {
		return dataSource;
	}
	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	/**
	 * @return the metadataFile
	 */
	public String getMetadataFile() {
		return metadataFile;
	}
	/**
	 * @param metadataFile the metadataFile to set
	 */
	public void setMetadataFile(String metadataFile) {
		this.metadataFile = metadataFile;
	}
	/**
	 * 〈一句话功能简述〉
	 * 
	 * @see java.lang.Object#toString()
	 * @return
	 */
	@Override
	public String toString() {
		return "APIinfo [apiName=" + apiName + ", selectId=" + selectId
				+ ", nameSpace=" + nameSpace + ", fullQualifiedName="
				+ fullQualifiedName + ", sqlText=" + sqlText + ", sqlType="
				+ sqlType + ", parameters=" + parameters + ", dataSource="
				+ dataSource + ", metadataFile=" + metadataFile + "]";
	}
	
	public Map<String, Object> toMap(){
		buildRestApiName();
		buildSoapApiName();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("apiName", this.apiName);
		map.put("soapApiName", this.soapApiName);
		map.put("restApiName", this.restApiName);
		map.put("selectId", this.selectId);
		map.put("nameSpace", this.nameSpace);
		map.put("fullQualifiedName", this.fullQualifiedName);
		map.put("sqlText", this.sqlText);
		map.put("sqlType", this.sqlType);
		map.put("parameters", this.parameters);
		map.put("dataSource", this.dataSource);
		map.put("metadataFile", this.metadataFile);
		return map;
	}
	
}
