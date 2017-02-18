/*
 * File Name: InstructionParaser.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-9-20

 * Modifier: Wuwuhao
 * Modify Date: 2016-9-20
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-9-20
 * @see       
 * @since     DSPlite V0.2.0
 */

public class InstructionParser {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InstructionParser.class);
	private static final Map<String, String> EXECUTROR = new HashMap<String, String>(); 
	static{
		EXECUTROR.put("clearRepos", "com.cetiti.dsp.util.InstructionExecutor");    //all executor must be static method 
		EXECUTROR.put("deleteService", "com.cetiti.dsp.util.InstructionExecutor"); //with only one String[].class arguments 
		EXECUTROR.put("createDatabase", "com.cetiti.dsp.util.InstructionExecutor");
		EXECUTROR.put("enableSecurityControl", "com.cetiti.dsp.util.Configration");
		EXECUTROR.put("enableQuantityControl", "com.cetiti.dsp.util.Configration");
	}
	
	
	/**
	 * all effective instructions
	 * 
	 * @return
	 */
	public static Set<String> instructionSet(){
		return EXECUTROR.keySet();
	}
	

	/**
	 * parse instruction like cmd-arg0,arg1,arg2
	 * 
	 * @param instruction
	 * @return
	 */
	public static String parse(String instruction){
		if(null==instruction){
			return null;
		}
		String op = null;
		String param = null;
		int sp = instruction.indexOf("-");
		if(sp<0){
			op = instruction.trim();
		}else if(sp==instruction.length()-1){
			op = instruction.substring(0, sp).trim();
		}else{
			op = instruction.substring(0, sp).trim();
			param = instruction.substring(sp+1).replaceAll(" ", "");
		}
		
		String cs = EXECUTROR.get(op);
		if(null!=cs){
			try {
				Class<?> clazz = Class.forName(cs);
				Method method = clazz.getMethod(op, String[].class);
				String[] args = param==null?null:param.split(",");
			    Object retn = method.invoke(null, (Object)args);
				if(null!=retn){
					cs = retn.toString();
				}else {
					cs = "executed";
				}
			} catch (Exception e) {
				//e.printStackTrace();
				cs = "exception";
				LOGGER.error(e.getMessage());
			}
		}
		
		return cs;
	}
	
}
