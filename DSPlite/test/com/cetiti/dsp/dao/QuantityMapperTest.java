/*
 * File Name: QuantityMapperTest.java
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
import org.junit.Ignore;
import org.junit.Test;

import com.cetiti.dsp.ds.DataSourceFactory;
import com.cetiti.dsp.model.QuantityInfo;
import com.cetiti.dsp.util.SequenceGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-10-21
 * @see       
 * @since     DSPlite V0.2.0
 */

public class QuantityMapperTest {

	private QuantityMapper mapper = DataSourceFactory.getMapper(QuantityMapper.class);
	/**
	 * Test method for {@link com.cetiti.dsp.dao.QuantityMapper#insert(com.cetiti.dsp.model.QuantityInfo)}.
	 */
	@Ignore
	public void testInsert() {
		QuantityInfo quantityInfo = new QuantityInfo();
		String s = SequenceGenerator.next();
		quantityInfo.setQuantityId(s);
		quantityInfo.setAppKey("key"+s);
		quantityInfo.setApiMethod("method"+s);
		quantityInfo.setTotal(100);
		quantityInfo.setUsed(10);
		quantityInfo.setCreationDate(new Date());
		mapper.insert(quantityInfo);
	}

	/**
	 * Test method for {@link com.cetiti.dsp.dao.QuantityMapper#update(com.cetiti.dsp.model.QuantityInfo)}.
	 */
	@Ignore
	public void testUpdate() {
		QuantityInfo quantityInfo = new QuantityInfo();
		String s = SequenceGenerator.next();
		quantityInfo.setQuantityId("1477039865173");
		quantityInfo.setAppKey("key"+s);
		quantityInfo.setApiMethod("method"+s);
		quantityInfo.setTotal(100);
		quantityInfo.setUsed(10);
		quantityInfo.setCreationDate(new Date());
		mapper.update(quantityInfo);
	}

	/**
	 * Test method for {@link com.cetiti.dsp.dao.QuantityMapper#selectList()}.
	 */
	@Ignore
	public void testSelectList() {
		mapper.selectList();
	}

	/**
	 * Test method for {@link com.cetiti.dsp.dao.QuantityMapper#select(java.lang.String, java.lang.String)}.
	 */
	@Ignore
	public void testSelectByKeyAndMethod() {
		
		System.out.println(mapper.selectByKeyAndMethod("key1477040072093", "method1477040072093"));
		
	}

	/**
	 * Test method for {@link com.cetiti.dsp.dao.QuantityMapper#select(java.lang.String)}.
	 */
	@Test
	public void testSelect() {
		System.out.println(mapper.select("1477039865173"));
	}

	/**
	 * Test method for {@link com.cetiti.dsp.dao.QuantityMapper#delete(java.lang.String)}.
	 */
	@Ignore
	public void testDelete() {
		mapper.delete("1477040629367");
	}

}
