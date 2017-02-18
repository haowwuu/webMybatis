/*
 * File Name: Configration.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-11-8

 * Modifier: Wuwuhao
 * Modify Date: 2016-11-8
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.util;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Properties;

import javax.xml.ws.Endpoint;

import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cetiti.dsp.util.IpPortUtil.Test;



/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-11-8
 * @see       
 * @since     DSPlite V0.2.0
 */

public enum Configration {
	
	INSTANCE;
	
	private final Logger LOGGER = LoggerFactory.getLogger(Configration.class);
	private final String configFile = "dsp.properties";
	
	private String adminName = "admin";
	private String adminPassword = "a66abb5684c45962d887564f08346e8d";
	private String serverName = null;
	private Integer soapPort = null;
	private boolean securityControlEnabled = false;
	private boolean quantityControlEnabled = false;
	private String externConfigFile = null;
	private long configFileLastModifiedTime = 0;
	
	private Configration(){
		watchAndConfig();
	}
	
	/**
	 * @return the serverName
	 */
	public String getServerName() {
		return serverName;
	}
	/**
	 * @return the soapPort
	 */
	public Integer getSoapPort() {
		return soapPort;
	}
	/**
	 * @return the securityControlEnabled
	 */
	public boolean isSecurityControlEnabled() {
		return securityControlEnabled;
	}

	/**
	 * @return the quantityControlEnabled
	 */
	public boolean isQuantityControlEnabled() {
		return quantityControlEnabled;
	}
	
	public String enableSecurityControl(String[] args) {
		if(null!=args&&args.length>0){
			securityControlEnabled = Boolean.parseBoolean(args[0]);
		}
		return "securityControlEnabled: "+String.valueOf(securityControlEnabled);
	}

	public String enableQuantityControl(String[] args) {
		if(null!=args&&args.length>0){
			quantityControlEnabled = Boolean.parseBoolean(args[0]);
		}
		return "quantityControlEnabled: "+String.valueOf(quantityControlEnabled);
	}
	
	public boolean adminLogin(String name, String password, String random){
		String pwd = EncryptUtil.md5(adminPassword+random);
		return adminName.equals(name)&&pwd.equals(password);
	}
	
	public boolean isAdmin(String name){
		return adminName.equals(name);
	}
	
	public void watchAndConfig(){
		try {
			File file = Resources.getResourceAsFile(configFile);
			if(null!=file&&file.lastModified()>configFileLastModifiedTime){
				configFileLastModifiedTime = file.lastModified();
				Properties properties = new Properties();
				properties.load(new FileReader(file));
				externConfigFile = properties.getProperty("externConfigFile");
				init(properties);
			}
			
			if(null!=externConfigFile){
				String externConfigFileUrl = null;
				File externFile = new File(externConfigFile);
				if(externFile.exists()){
					externConfigFileUrl = externFile.toURI().toURL().toString();					
				}else{
					URL url = Resources.getResourceURL(externConfigFile);
					externConfigFileUrl = url.toString();
				}
				Properties externProperties  = Resources.getUrlAsProperties(externConfigFileUrl);
				init(externProperties);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	private void init(Properties prop){
		if(null==prop){
			return;
		}
		adminName = prop.getProperty("adminName", adminName);
		adminPassword = prop.getProperty("adminPassword", adminPassword);
		serverName = prop.getProperty("serverName");
		String portStr = prop.getProperty("soapPort");
		if(null!=portStr){
			try {
				soapPort = Integer.parseInt(portStr.trim());
			} catch (Exception e) {
				LOGGER.error("soapPort:"+portStr +" numberformatexception");
			}
		}
		if(null!=serverName){
			String soapServer = serverName;
			if(null!=soapPort){
				soapServer += ":"+soapPort;
			}
			try {
				Endpoint endpoint = Endpoint.publish("http://"+soapServer+"/test", new Test());
				endpoint.stop();
			} catch (Exception e) {
				serverName = null;
				LOGGER.error("init configuration test soap server["+soapServer+"]"+e.getMessage());
			}
		}
		String se = prop.getProperty("securityControlEnabled");
		if(null!=se){
			securityControlEnabled = Boolean.parseBoolean(se);
		}
		String qe = prop.getProperty("securityControlEnabled");
		if(null!=se){
			quantityControlEnabled = Boolean.parseBoolean(qe);
		}
	}
	
}
