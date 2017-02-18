/*
 * File Name: APIformXMl.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-8-26

 * Modifier: Wuwuhao
 * Modify Date: 2016-8-26
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-8-26
 * @see       
 * @since     DSPlite V0.2.0
 */

public class APIformXMl {	
	
	protected static SqlSessionFactory sqlSessionFactory;
	
	private static void InitConn(){
		
		try {
			Reader configReader = Resources.getResourceAsReader("Config.xml");
		    // Reader configReader = Resources.getUrlAsReader("http://10.0.10.78:8080/wserp/repos/test/Config.xml");
		    sqlSessionFactory = new SqlSessionFactoryBuilder().build(configReader);
		    configReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void selectUsers(){
		if(null==sqlSessionFactory){
			InitConn();
		}
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Map> bs = sqlSession.selectList("com.cetiti.mybatis.Mapper.selectUsers");
		System.out.println(bs);
	}
	
	public static void showAllMethod(){
		if(null==sqlSessionFactory){
			InitConn();
		}
		for(String name:sqlSessionFactory.getConfiguration().getMappedStatementNames()){
			System.out.println(name);
		}
			
	}
	
	public static void main(String[] args){
		showAllMethod();
		selectUsers();
	}
	
}
