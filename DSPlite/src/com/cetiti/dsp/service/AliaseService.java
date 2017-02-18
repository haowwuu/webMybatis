/*
 * File Name: AppService.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-10-31

 * Modifier: Wuwuhao
 * Modify Date: 2016-10-31
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.service;

import com.cetiti.dsp.model.KeyValue;



/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-10-31
 * @see       
 * @since     DSPlite V0.2.0
 */

public interface AliaseService {
	
	int setAliase(KeyValue<String, String> aliaseInfo);
	
	String getAliase(String serviceName);
	
}
