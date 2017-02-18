/*
 * File Name: AuditTrailMapperTest.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-10-21

 * Modifier: Wuwuhao
 * Modify Date: 2016-10-21
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.cetiti.dsp.ds.DataSourceFactory;
import com.cetiti.dsp.model.APIinfo;
import com.cetiti.dsp.model.AuditTrail;
import com.cetiti.dsp.util.SequenceGenerator;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-10-21
 * @see       
 * @since     DSPlite V0.2.0
 */

public class AuditTrailMapperTest {

	private AuditTrailMapper mapper = DataSourceFactory.getMapper(AuditTrailMapper.class);

	/**
	 * Test method for {@link com.cetiti.dsp.dao.AuditTrailMapper#insert(com.cetiti.dsp.model.AuditTrail)}.
	 */
	@Test
	public void testInsert() {
		AuditTrail audit = new AuditTrail();
		String s = SequenceGenerator.next();
		audit.setAuditId(s);
		audit.setAppKey("key"+s);
		audit.setApiMethod("method"+s);
		audit.setApiType((int)System.nanoTime()%2);
		audit.setParameters("parameters"+s);
		audit.setAuditDate(new Date());
		
		mapper.insert(audit);
	}

	/**
	 * Test method for {@link com.cetiti.dsp.dao.AuditTrailMapper#selectList()}.
	 */
	@Test
	public void testSelectList() {
		for(AuditTrail audit:mapper.selectList()){
			System.out.println(audit);
		}
	}

}
