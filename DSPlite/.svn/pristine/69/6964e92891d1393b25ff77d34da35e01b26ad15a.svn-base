/*
 * File Name: URITest.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-10-12

 * Modifier: Wuwuhao
 * Modify Date: 2016-10-12
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp;

import java.io.File;
import java.io.Reader;
import java.net.URI;
import java.net.URL;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Ignore;
import org.junit.Test;

import com.cetiti.dsp.soap.ServicePublisher;
import com.cetiti.dsp.builder.SqlSessionFactoryBuilder;
import com.cetiti.dsp.util.MapperConfigReader;
import com.cetiti.dsp.util.SqlSessionFactoryRepos;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-10-12
 * @see       
 * @since     DSPlite V0.2.0
 */

public class URITest {
	
	@Test
	public void test()throws Exception{
		/*URI uri = new URI("string:///test");
		System.out.println(uri.toString());
		System.out.println(uri.getScheme());*/
		File file = new File("C:\\Users\\Administrator\\Desktop\\DSPLite\\xml\\ssoMapper.xml");
		String uri=null, url=null;
		if(file.exists()){
			uri = file.toURI().toString();
			url = file.toURI().toURL().toString();
		}
		System.out.println("uri: "+uri);
		System.out.println("url: "+url);
	}
	
	@Ignore
	public void test2(){
		String resource = "DspMapper.xml";
		try {
			Reader configReader = Resources.getResourceAsReader(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configReader);
			SqlSessionFactoryRepos.addSqlSessionFactory(sqlSessionFactory, resource);
			configReader.close();
		} catch (Exception e) {
			System.err.println(MapperConfigReader.class.getCanonicalName()+
					"-InitConn(): load resource:"+resource +e.getMessage());
		}
		
		ServicePublisher.publish(SqlSessionFactoryRepos.getApiInfos());
	}

}
