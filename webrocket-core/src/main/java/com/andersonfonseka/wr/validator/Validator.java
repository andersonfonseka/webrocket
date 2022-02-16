package com.andersonfonseka.wr.validator;

import com.andersonfonseka.wr.application.WebApplication;
import com.andersonfonseka.wr.components.Component;
import com.andersonfonseka.wr.components.Input;
import com.andersonfonseka.wr.message.Message;

public abstract class Validator {
	
	private WebApplication webApplication;
	
	private Message message;
	
	private Component component;
	
	public void setComponent(Input input) {
		this.component = input;
	}

	public Component getComponent() {
		return component;
	}
	
	public Message getMessageBundle() {
		return message;
	}
	
	public WebApplication getWebApplication() {
		return webApplication;
	}

	public void setWebApplication(WebApplication webApplication) {
		this.webApplication = webApplication;
		message = this.webApplication.getMessageBundle().getBundle(this.webApplication.getDefaultMessageBundle());
	}

	public abstract ValidatorMessage doValidate();
	
}
