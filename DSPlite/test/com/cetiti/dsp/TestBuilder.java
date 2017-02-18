/*
 * File Name: TestBuilder.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-9-18

 * Modifier: Wuwuhao
 * Modify Date: 2016-9-18
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.net.aso.e;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.cetiti.dsp.builder.SqlSessionFactoryBuilder;
import com.cetiti.dsp.util.SqlSessionFactoryRepos;
import com.cetiti.dsp.util.SqlSessionFactoryWapper;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-9-18
 * @see       
 * @since     DSPlite V0.2.0
 */

public class TestBuilder {
	
	private SqlSessionFactory loadResource(String resource)throws Exception{
		Reader configReader = Resources.getResourceAsReader(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configReader);
		SqlSessionFactoryRepos.addSqlSessionFactory(sqlSessionFactory, resource);
		configReader.close();
		
		return sqlSessionFactory;
	}
	@Ignore
	public void test() throws Exception{
		assertEquals(0, SqlSessionFactoryRepos.size());
		SqlSessionFactory ssf = loadResource("DspMapper1.xml");
		assertEquals(1, SqlSessionFactoryRepos.size());
		SqlSession session = ssf.openSession();
		List<Map> result = session.selectList("com.cetiti.dsp.Mapper.selectLevels");
		System.out.println(result);
		ssf = loadResource("DspMapper.xml");
		assertEquals(1, SqlSessionFactoryRepos.size());
		session = ssf.openSession();
		result = session.selectList("com.cetiti.dsp.Mapper.selectStrategys");
		System.out.println(result);
		result = session.selectList("com.cetiti.dsp.Mapper.selectLevels");
		System.out.println(result);
	}
	
	@Ignore
	public void testSessionCache() throws Exception{
		assertEquals(0, SqlSessionFactoryRepos.size());
		SqlSessionFactory ssf = loadResource("DspMapper1.xml");
		ssf = new SqlSessionFactoryWapper(ssf);
		assertEquals(1, SqlSessionFactoryRepos.size());
		SqlSession session = ssf.openSession();
		List<Map> result = session.selectList("com.cetiti.dsp.Mapper.selectLevels");
		System.out.println(result);
		ssf = loadResource("DspMapper.xml");
		ssf = new SqlSessionFactoryWapper(ssf);
		assertEquals(1, SqlSessionFactoryRepos.size());
		session = ssf.openSession();
		result = session.selectList("com.cetiti.dsp.Mapper.selectStrategys");
		System.out.println(result);
		result = session.selectList("com.cetiti.dsp.Mapper.selectLevels");
		System.out.println(result);
	}
	
	@Test
	public void testMap(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "value");
		try {
			String nul = map.get(null);
			String v = map.get("key");
			map.remove("key");
			System.out.println(nul+v);
			map.remove("key");
			System.out.println("last:"+v);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally{
			System.out.println("finally");
		}
	}
}
