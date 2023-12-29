package com.hexaware.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
	private static final String PROPERTY_FILE = "config.properties";
	private PropertyUtil()
	{
		
	}
	public static String getPropertyString()
	{
		Properties p =new Properties();
		try(InputStream input =PropertyUtil.class.getClassLoader().getResourceAsStream(PROPERTY_FILE))
		{
			
			if(input==null) {
			throw new RuntimeException("Sorry, unable to find " + PROPERTY_FILE);
			}
		p.load(input);
		/*String hostname = p.getProperty("jdbc.hostname");
        String dbName = p.getProperty("jdbc.dbname");
        String username = p.getProperty("jdbc.uname");
        String password = p.getProperty("jdbc.password");
        String port = p.getProperty("jdbc.port");*/
		String s= "jdbc:mysql://" + p.getProperty("jdbc.hostname") + ":" + p.getProperty("jdbc.port")
        + "/" + p.getProperty("jdbc.dbname") + "?user=" + p.getProperty("jdbc.uname") + "&password=" + p.getProperty("jdbc.password");
		
		return s;
	}catch(IOException e)
		{
		e.printStackTrace();
		throw new RuntimeException("Error reading property file " + PROPERTY_FILE);
		}
	}
}
	
