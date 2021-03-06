package com.andersonfonseka.wr.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andersonfonseka.wr.application.WebApplication;
import com.andersonfonseka.wr.components.Button;
import com.andersonfonseka.wr.components.Component;
import com.andersonfonseka.wr.components.Input;
import com.andersonfonseka.wr.components.WebForm;
import com.andersonfonseka.wr.components.WebPage;

public class WebRocketServlet extends HttpServlet {

	private WebApplication webApplication;
	
	private static final long serialVersionUID = -6154475799000019575L;
	
	public WebRocketServlet() {
		super();
	}
	
	public WebRocketServlet(WebApplication webApplication) {
		super();
		this.webApplication = webApplication;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// For Tomcat Servers
		if (null == webApplication) {
			
			String wbAppClass =  request.getServletContext().getInitParameter("webAppClass");
			
			try {
				this.webApplication = (WebApplication) Class.forName(wbAppClass).newInstance();
				this.webApplication.setContext(getServletContext().getContextPath());
				getServletContext().setAttribute("webApplication", this.webApplication);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		if (request.getParameter("op") != null) {
			
			InputStream in = this.getClass().getClassLoader().getResourceAsStream(request.getParameter("op"));
			BufferedReader bis = new BufferedReader(new InputStreamReader(in));
			
			response.setContentType("text/css");
			response.setStatus(HttpServletResponse.SC_OK);
			

			int cnt = 0;
			final char[] buf = new char[163873];
			
			while (bis.read(buf) != -1) {
				cnt++;
			}
			
			in.close();
			
		    response.getWriter().write(buf);  
		    response.flushBuffer();
		      
		} else {

			
			response.setContentType("text/html");
			response.setStatus(HttpServletResponse.SC_OK);
				
			WebPage webpage = this.webApplication.getStartPage();
			webpage.setWebApplication((WebApplication) getServletContext().getAttribute("webApplication"));
			webpage.add(webpage.createForm());
			
			request.getSession().setAttribute("_last", webpage);
			response.getWriter().println(webpage.doRender());
			
		}
		
		
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map<String, String[]> params = request.getParameterMap();

		WebPage wp = (WebPage) request.getSession().getAttribute("_last");
		Component comp = wp.getComponentById(params.get("form")[0]);
		
		if (comp instanceof WebForm) {
			
			WebForm webForm = (WebForm) comp;
			webForm.clearMessages();
			
			Button button = (Button) webForm.getComponentById(params.get("submitId")[0]);
			
			if (!button.isImmediate()) {
				//ApplyValues
				applyValues(params, webForm);
				//Validate
				validate(webForm, wp.getWebApplication());
				
				if (!webForm.getValidators().isEmpty()) {
					webForm.validate(params);
				}
			}
						
			//runAction
			if (webForm.getMessages().isEmpty()) {
				runAction(response, request, params, button);
			} else {
				response.setContentType("text/html");
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().println(wp.doRender());
			}
			
		} else {
			
			WebApplication webApplication = (WebApplication) getServletContext().getAttribute("webApplication");
			wp = webApplication.getErrorPage(); 
			request.getSession().setAttribute("_last", wp);
			
			response.setContentType("text/html");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println(wp.doRender());

		}

	}

	private void validate(Component webForm, WebApplication webApplication) {
		for (Component component: webForm.getComponents()) {
			if (component instanceof Input) {
				Input input = (Input) component;
				input.validate(webApplication);
			} else {
				validate(component, webApplication);
			}
		}
	}

	private void applyValues(Map<String, String[]> params, Component webForm) {
		
		for (Component component: webForm.getComponents()) {
			if (component instanceof Input) {
				Input input = (Input) component;
				if (params.containsKey(input.getId())) {
					input.setValue(params.get(input.getId())[0]);
				}
			} else {
				applyValues(params, component);
			}
		}
	}
	
	private void runAction(HttpServletResponse response, HttpServletRequest request, Map<String, String[]> params, Button button) {
		
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		
		Map<String, String[]> actionParams = new HashMap<String, String[]>(params);
		
		Button input = (Button) button;
		
		if (null != input.getWebAction()) {
			try {
				
				if (!input.getParams().isEmpty()) {
					actionParams.putAll(input.getParams());
				}
				
				WebPage webpage = input.getWebAction().execute(actionParams);

				
				if (null != webpage) {
					
					if (webpage.getStatus() == WebPage.NEW) {

						request.getSession().setAttribute("_last", webpage);
						webpage.setWebApplication((WebApplication) getServletContext().getAttribute("webApplication"));
						webpage.add(webpage.createForm());
						webpage.setStatus(WebPage.CREATED);
						
					} else if (webpage.getStatus() == WebPage.CREATED) {
						webpage = (WebPage) request.getSession().getAttribute("_last");
					}
					
					if (webpage.isBlnOnLoad()) {
						webpage.onLoad(webpage.getParams());
					}
					
					response.getWriter().println(webpage.doRender());
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
