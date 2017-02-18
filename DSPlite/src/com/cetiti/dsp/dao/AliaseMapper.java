/*
 * File Name: aliaseMaliaseer.java
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
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cetiti.dsp.model.KeyValue;

/**
 */

public interface AliaseMapper {
	
	@Select("select * from aliase_info")
	@Results({ 
		@Result(property = "key", column = "original"), 
	    @Result(property = "value", column = "aliase")
	})
	List<KeyValue<String, String>> selectList();
  
	@Select("select * from aliase_info where original = #{key}")
	@Results({ 
	    @Result(property = "key", column = "original"), 
        @Result(property = "value", column = "aliase") 
	})
	KeyValue<String, String> select(String key);
			
	@Insert("insert into aliase_info (original, aliase) values (#{key}, #{value})")
	int insert(KeyValue<String, String> aliaseInfo);
	
	@Update("update aliase_info set original = #{key},aliase = #{value} where original = #{key}")
	int update(KeyValue<String, String> aliaseInfo);
	
	@Delete("delete from aliase_info where original = #{key}")
	int delete(String key);
}
