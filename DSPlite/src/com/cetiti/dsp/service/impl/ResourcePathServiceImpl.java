/*
 * File Name: ResourcePathServiceImpl.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-9-26

 * Modifier: Wuwuhao
 * Modify Date: 2016-9-26
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.service.impl;

import java.util.List;

import com.cetiti.dsp.dao.ResourceMapper;
import com.cetiti.dsp.ds.DataSourceFactory;
import com.cetiti.dsp.service.ResourcePathService;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-9-26
 * @see       
 * @since     DSPlite V0.2.0
 */

public class ResourcePathServiceImpl implements ResourcePathService {

	private ResourceMapper resourceMapper = DataSourceFactory.getMapper(ResourceMapper.class);
	
	@Override
	public void save(String path) {
		if(null==resourceMapper.select(path)){
			resourceMapper.insert(path);
		}
	}

	@Override
	public List<String> load() {
		return resourceMapper.selectList();
	}

	@Override
	public int delete(String path) {
		return resourceMapper.delete(path);
	}
	
	@Override
	public void clear(){
		resourceMapper.deleteAll();
	}
}
