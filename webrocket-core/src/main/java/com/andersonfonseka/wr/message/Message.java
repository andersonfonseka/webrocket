package com.andersonfonseka.wr.message;

import java.io.IOException;
import java.util.Properties;

public class Message {
	
	private Properties prop = new Properties();
	
	private String fileName;
	
	public Message(String fileName) {
		
		this.fileName = fileName;
		
		try {
			prop.load(Message.class.getResourceAsStream(this.fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getMessage(String key) {
		return this.prop.getProperty(key);
	}

}
