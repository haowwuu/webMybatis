/*
 * File Name: QuantityService.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-11-2

 * Modifier: Wuwuhao
 * Modify Date: 2016-11-2
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.service;

import java.util.List;
import java.util.Map;

import com.cetiti.dsp.model.QuantityInfo;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-11-2
 * @see       
 * @since     DSPlite V0.2.0
 */

public interface QuantityService {
	
	List<QuantityInfo> queryAll();
	
	void add(QuantityInfo quantity);
	
	void update(QuantityInfo quantity);
	
	void delete(String quantityId);
	
	List<Map<String, Object>> queryQuantityAndServiceByAppName(String appName);
	
	void resetQuantity();
}
