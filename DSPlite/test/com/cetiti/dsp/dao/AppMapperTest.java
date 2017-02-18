/*
 * File Name: AppMapperTest.java
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

import org.junit.Ignore;
import org.junit.Test;

import com.cetiti.dsp.ds.DataSourceFactory;
import com.cetiti.dsp.model.APPinfo;
import com.cetiti.dsp.util.SequenceGenerator;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-10-21
 * @see       
 * @since     DSPlite V0.2.0
 */

public class AppMapperTest {
	
	private AppMapper appMapper = DataSourceFactory.getMapper(AppMapper.class);
	

	/**
	 * Test method for {@link com.cetiti.dsp.dao.AppMapper#selectList()}.
	 */
	@Test
	public void testSelectList() {
		appMapper.selectList();
	}

	/**
	 * Test method for {@link com.cetiti.dsp.dao.AppMapper#select(java.lang.String)}.
	 */
	@Test
	public void testSelect() {
		APPinfo app = appMapper.select("key123");
		System.out.println(app);
	}

	/**
	 * Test method for {@link com.cetiti.dsp.dao.AppMapper#insert(com.cetiti.dsp.model.APPinfo)}.
	 */
	@Ignore
	public void testInsert() {
		APPinfo app = new APPinfo();
		long key = System.nanoTime();
		app.setAppId(SequenceGenerator.next());
		app.setAppKey("key"+key);
		app.setAppSecret("secret"+key);
		app.setAppName("app"+key);
		app.setAppAddress("www."+key+".com");
		app.setAppAdmin("admin"+key);
		app.setAdminEmail("email@"+key+".com");
		app.setAdminTel("18812344321");
		app.setDescription("desc"+key);
		appMapper.insert(app);
	}

	/**
	 * Test method for {@link com.cetiti.dsp.dao.AppMapper#update(com.cetiti.dsp.model.APPinfo)}.
	 */
	@Test
	public void testUpdate() {
		APPinfo app = new APPinfo();
		long key = System.nanoTime();
		app.setAppId("21863387261057");
		app.setAppKey("key"+key);
		app.setAppSecret("secret"+key);
		app.setAppName("app"+key);
		app.setAppAddress("www."+key+".com");
		app.setAppAdmin("admin"+key);
		app.setAdminEmail("email@"+key+".com");
		app.setAdminTel("18812344321");
		app.setDescription("desc"+key);
		appMapper.update(app);
	}
	
	@Test
	public void testDelete(){
		appMapper.delete("21863387261057");
	}

}
