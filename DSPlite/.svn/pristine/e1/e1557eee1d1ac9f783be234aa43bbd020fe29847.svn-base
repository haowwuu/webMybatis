/*
 * File Name: AuditTrailService.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-10-25

 * Modifier: Wuwuhao
 * Modify Date: 2016-10-25
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cetiti.dsp.model.AuditTrail;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-10-25
 * @see       
 * @since     DSPlite V0.2.0
 */

public interface AuditTrailService {
	
	int trail(AuditTrail auditTrail);
	
	public Map<String, List<Object[]>> statisticByApi(Date begin, Date end, String api, int topN);
	
	public Map<String, List<Object[]>> statisticByKey(Date begin, Date end, String key, int topN);
	
	void cleanOld();
}
