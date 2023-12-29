package com.hexaware.util;

import java.sql.*;

public class DBConnection {
	private static Connection con;
	private DBConnection()
	{
		
	}
	public static Connection getConnection() throws ClassNotFoundException
	{
		try {
			if(con==null || con.isClosed())
			{
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					String s=PropertyUtil.getPropertyString();
					con = DriverManager.getConnection(s);
				} catch(ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}