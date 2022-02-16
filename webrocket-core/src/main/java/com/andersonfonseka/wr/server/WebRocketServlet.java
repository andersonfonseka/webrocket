package com.andersonfonseka.wr.server;

import java.io.IOException;
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
	
	public WebRocketServlet(WebApplication webApplication) {
		super();
		this.webApplication = webApplication;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
			
		WebPage webpage = this.webApplication.getStartPage();
		webpage.setWebApplication((WebApplication) getServletContext().getAttribute("webApplication"));
		webpage.add(webpage.createForm());
		
		request.getSession().setAttribute("_last", webpage);
		response.getWriter().println(webpage.doRender());
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map<String, String[]> params = request.getParameterMap();

		WebPage wp = (WebPage) request.getSession().getAttribute("_last");
		Component comp = wp.getComponents().stream().filter(x -> x.getId().equals(params.get("form")[0])).findFirst().get();
		
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
		
		Button input = (Button) button;
		
		if (null != input.getWebAction()) {
			try {
				
				WebPage webpage = input.getWebAction().execute(params);

				
				if (null != webpage) {
					request.getSession().setAttribute("_last", webpage);
				} else {
					webpage = (WebPage) request.getSession().getAttribute("_last");
				}
				
				webpage.setWebApplication((WebApplication) getServletContext().getAttribute("webApplication"));
				webpage.add(webpage.createForm());
				
				if (webpage.isBlnOnLoad()) {
					webpage.onLoad(params);
				}
				
				response.getWriter().println(webpage.doRender());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
