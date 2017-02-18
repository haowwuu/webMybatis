package com.cetiti.dsp.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.cetiti.dsp.ds.DataSourceFactory;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-9-26
 * @see       
 * @since     DSPlite V0.2.0
 */
public class ResourceMapperTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSelectList() throws Exception{
		DataSourceFactory.createSchemaAndLoadData();
		ResourceMapper mapper = DataSourceFactory.getMapper(ResourceMapper.class);
		String resourcexml = "test.xml";
		assertEquals(1, mapper.insert(resourcexml));
		System.out.println(mapper.selectList());
		assertTrue(mapper.selectList().contains(resourcexml));
		assertEquals(resourcexml, mapper.select(resourcexml));
		assertEquals(1, mapper.delete(resourcexml));
		assertNull(mapper.select(resourcexml));
	}

	@Ignore
	public void testSelect() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testInsert() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testDelete() {
		fail("Not yet implemented");
	}

}
