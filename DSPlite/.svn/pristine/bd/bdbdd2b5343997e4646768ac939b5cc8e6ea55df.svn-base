/*
 * File Name: JavaStringCompilerTest.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-11-4

 * Modifier: Wuwuhao
 * Modify Date: 2016-11-4
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.soap.compiler;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-11-4
 * @see       
 * @since     DSPlite V0.2.0
 */

public class JavaStringCompilerTest {

	/**
	 * 〈一句话功能简述〉
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Ignore
	public void test() throws Exception {
		URL classPath = Thread.currentThread().getContextClassLoader().getResource("");
		File file = new File(classPath.toURI());
		String filePath = file.getPath();
		System.out.println(filePath);
		String sysPath = System.getProperty("java.class.path");
		
		int i = sysPath.indexOf(".jar");
		System.out.println(sysPath.charAt(i+4));
		
		System.out.println(sysPath);
	}
	
	@Test
	public void testCompiler(){
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		System.out.println(compiler);
		System.out.println(System.getProperty("java.home"));
	}

}
