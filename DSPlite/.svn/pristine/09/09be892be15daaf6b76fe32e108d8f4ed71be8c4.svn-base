/*
 * File Name: XmlResultFormater.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-8-28

 * Modifier: Wuwuhao
 * Modify Date: 2016-8-28
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-8-28
 * @see       
 * @since     DSPlite V0.2.0
 */

public class XmlResultFormater implements ApiResultFormater{

	private XStream xStream;
	
	public XmlResultFormater() {
		xStream = new XStream(new DomDriver());
	}

	@Override
	public String format(Object obj) {
		
		return xStream.toXML(obj);
	}
	
}
