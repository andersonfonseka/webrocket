package com.andersonfonseka.wr.action;

import java.util.Map;

import com.andersonfonseka.wr.components.WebPage;

public abstract class WebAction {
	
	public WebAction() {
		super();
	}

	public abstract WebPage execute(Map<String, String[]> params);

}
