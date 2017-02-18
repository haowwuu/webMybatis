/*
 * File Name: DspMapper.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-9-22

 * Modifier: Wuwuhao
 * Modify Date: 2016-9-22
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-9-22
 * @see       
 * @since     DSPlite V0.2.0
 */

public interface ResourceMapper {
	
	@Select("select resource_url from service_resource")
	List<String> selectList();
	
	@Select("select resource_url from service_resource where resource_url = #{resource}")
	String select(String resource);
	
	@Insert("insert into service_resource (resource_url) values (#{resource})")
	int insert(String resource);
	
	@Delete("delete from service_resource where resource_url = #{resource}")
	int delete(String resource);
	
	@Delete("delete from service_resource")
	int deleteAll();

}
