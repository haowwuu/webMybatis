/*
 * File Name: SQLlite.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-9-21

 * Modifier: Wuwuhao
 * Modify Date: 2016-9-21
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


import SQLite.Callback;
import SQLite.Database;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-9-21
 * @see       
 * @since     DSPlite V0.2.0
 */
//http://www.ch-werner.de/javasqlite/
//DBeaver   
public class SQLlite {
	
	private Database db;
	
	static final String strCreate ="create table user (userid integer primary key, username text)";  
    static final String strInsert ="insert into user values (2,'James')";  
    static final String strDisplay ="select * from user"; 
	
	@Before
	public void setup(){
		System.setProperty("SQLite.library.path", Class.class.getClass().getResource("/").getPath());
		System.out.println("path:"+System.getProperty("SQLite.library.path"));
		db = new Database();
	}
	
	
	public void version(){
		System.out.println(Database.version());
	}
	
	public void connectDb(String dbf) throws Exception{
		db.open(dbf, 0700);
	}
	
	private int excute(String sql){
		try {
			db.exec(sql, new TableFmt());
			return 1;
		} catch (java.lang.Exception e) {
			e.printStackTrace();
			return -1;
		}
	}	
	
	@Ignore
	public void testdb() throws Exception {	
		version();
		connectDb("dspdb");
		excute(strCreate);
		excute(strInsert);
		excute(strDisplay);
	}
	
	

	class TableFmt implements Callback{
		public void columns (String[] cols){
			System.out.println("columns");
			for (int i = 0; i < cols.length; i++) {
				System.out.println(cols[i]);
			}
		}
		public boolean newrow(String[] cols){
			System.out.println("newrow");
			for (int i = 0; i < cols.length; i++) {
				System.out.println(cols[i]);
			}
			return false;
		}
		
		public void types(String[] cols){
			System.out.println("types");
			for (int i = 0; i < cols.length; i++) {
				System.out.println(cols[i]);
			}
		}
	}
	
	@Test
	public void testJdbc() throws Exception{
		Class.forName("SQLite.JDBCDriver");
		//Connection conn = DriverManager.getConnection("jdbc:sqlite:/E:/MyEclipse/workSpace/DSPlite/dspdb");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:/dsp.db");
		Statement stat = conn.createStatement();
		stat.executeUpdate("drop table if exists people;");
		stat.executeUpdate("create table people(name, occupation)");
		PreparedStatement ps = conn.prepareStatement("insert into people values (?, ?);");
		ps.setString(1, "yong");
		ps.setString(2, "l1");
		ps.execute();
		ps.addBatch();
		ps.setString(1, "qiong");
		ps.setString(2, "l2");
		ps.addBatch();
		ps.setString(1, "peng");
		ps.setString(2, "l3");
		ps.addBatch();
		
		
		ps.executeBatch();
		
		ResultSet rs = stat.executeQuery("Select * from people;");
		while(rs.next()){
			System.out.println("name="+rs.getString("name"));
			System.out.println("title="+rs.getString("occupation"));
		}
		System.out.println("finished");
		rs.close();
		conn.close();
		
	}


}
