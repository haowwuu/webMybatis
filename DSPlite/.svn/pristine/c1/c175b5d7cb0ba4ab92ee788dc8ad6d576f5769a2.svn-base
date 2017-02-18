/*
 * File Name: SecurityQuantityService.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-10-24

 * Modifier: Wuwuhao
 * Modify Date: 2016-10-24
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.service;

import java.util.Map;

import com.cetiti.dsp.util.ApiResult;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-10-24
 * @see       
 * @since     DSPlite V0.2.0
 */

public interface SecurityQuantityService {
	
	static final String APP_KEY = "appKey";
	static final String TIMESTAMP = "timestamp";
	static final String SIGN = "sign";
	static final int TYPE_SOAP = 0;
	static final int TYPE_REST = 1;
	
	ApiResult securityCheck(Map<String, String> paramMap);
	
	ApiResult quantityCheck(String appKey, String apiName);
}
