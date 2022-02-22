package com.andersonfonseka.wr.util;

import java.util.Iterator;
import java.util.Map;

public class BeantUtil {
	
	public static String getComponentValue(Map<String, String[]> params, String id) {
		
		 String result = "";
		
		 Iterator<String> it = params.keySet().iterator();
		 
		 while(it.hasNext()) {
			 
			 String key = it.next();
			 
			 if (key.contains(id)) {
				 result = params.get(key)[0];
			 }
			 
		 }
		 
		 return result;
		
	}

}
