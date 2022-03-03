package com.andersonfonseka.wr.application;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.jetty.util.component.AbstractLifeCycle;

import com.andersonfonseka.wr.components.WebPage;
import com.andersonfonseka.wr.message.Message;
import com.andersonfonseka.wr.message.MessageBundle;
import com.andersonfonseka.wr.server.WebRocketServer;

public class WebApplication {

	private String context;

	private int port;

	private String title;

	private WebRocketServer server;
	
	private MessageBundle messageBundle;
	
	private String defaultMessageBundle;
	
	private Class<? extends WebPage> startPage;
	
	private Class<? extends WebPage> errorPage;
	
	private List<Class<? extends WebPage>> components = new ArrayList<Class<? extends WebPage>>();

	public WebApplication(String context, int port, String title) {
		super();
		this.context = context;
		this.port = port;
		this.title = title;

		try {
			this.server = new WebRocketServer(getPort(), getContext(), this);
			
			Logger log = Logger.getAnonymousLogger();
			
			log.info("========================================================================");
			log.info("Please access http://localhost:" + this.port +  this.context);
			log.info("========================================================================");

			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public void add(String key, Message message) {
		messageBundle.add(key, message);
	}

	public void add(Class<? extends WebPage> component) {
		this.components.add(component);
	}
	
	public List<Class<? extends WebPage>> getComponents() {
		return components;
	}
	
	public MessageBundle getMessageBundle() {
		return messageBundle;
	}
	
	public void setMessageBundle(MessageBundle messageBundle) {
		this.messageBundle = messageBundle;
	}
	
	public void setContext(String context) {
		this.context = context;
	}


	public void start() {

		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void stop() {

		try {
			this.server.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public boolean isRunning() {

		boolean result = false;
		
		try {
			result = this.server.status().equals(AbstractLifeCycle.STARTED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;

	}


	public String getContext() {
		return context;
	}

	public int getPort() {
		return port;
	}

	public String getTitle() {
		return title;
	}

	public WebPage getStartPage() {
		try {
			return startPage.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public void setStartPage(Class<? extends WebPage> startPage) {
		this.startPage = startPage;
	}

	public WebPage getErrorPage() {
		
		WebPage wp = null;
		
		try {
			wp = errorPage.newInstance();
			wp.setWebApplication(this);
			wp.add(wp.createForm());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return wp;
	}


	public void setErrorPage(Class<? extends WebPage> errorPage) {
		this.errorPage = errorPage;
	}


	public String getDefaultMessageBundle() {
		return defaultMessageBundle;
	}


	public void setDefaultMessageBundle(String defaultMessageBundle) {
		this.defaultMessageBundle = defaultMessageBundle;
	}
	
	
	
}
