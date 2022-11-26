package com.restapi.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties prop;
	String path = System.getProperty("user.dir") + "//Config//File.properties";

	public ReadConfig() {

		prop = new Properties();

		File file = new File(path);
		try {
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public String getEmpId() {
		
		String eid = prop.getProperty("EmployeeID");
		if(eid!=null)
			return eid;
		else
			throw new RuntimeException("Employee ID Not Found In Properties File");
	}
	
	public String getUrl() {
		
		String url = prop.getProperty("URL");
		if(url!=null)
			return url;
		else
			throw new RuntimeException("Employee ID Not Found In Properties File");
	}
	
	

}
