/*
 * File Name: ApiResultFormaterFactory.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-8-28

 * Modifier: Wuwuhao
 * Modify Date: 2016-8-28
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.util;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-8-28
 * @see       
 * @since     DSPlite V0.2.0
 */

public class ApiResultFormaterFactory {

	public static ApiResultFormater getApiResultFormater(String format){
		if("xml".equalsIgnoreCase(format)){
			return new XmlResultFormater();
		}else {
			return new JsonResultFormater();
		}
	}
	
	public static ApiResultFormater getApiResultFormater(){
		return getApiResultFormater(null);
	}
}
