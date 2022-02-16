package com.andersonfonseka.wr.navigation;

import java.util.Map;

import com.andersonfonseka.wr.components.Component;
import com.andersonfonseka.wr.components.WebPage;

public class WebNavigation {

	public static WebPage gotoPage(Class<? extends Component> clazz) {
		
		WebPage wp = null;
		try {
			wp = (WebPage) clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return wp;
		
	}
	
	public static WebPage gotoPage(Class<? extends Component> clazz, Map<String, String[]> params) {
		
		WebPage wp = null;
		try {
			wp = (WebPage) clazz.newInstance();
			wp.setBlnOnLoad(true);
			wp.setParams(params);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return wp;
		
	}

}
