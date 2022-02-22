package com.andersonfonseka.wr.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.component.AbstractLifeCycle;

import com.andersonfonseka.wr.application.WebApplication;

public class WebRocketServer {

	private Server server;

	public WebRocketServer(int port, String context, WebApplication webApplication) throws Exception {

		server = new Server(port);

		ServletContextHandler servletContext = new ServletContextHandler(ServletContextHandler.SESSIONS);

		servletContext.setContextPath(context);

		server.setHandler(servletContext);

		servletContext.addServlet(new ServletHolder(new WebRocketServlet(webApplication)), "/*");

		servletContext.setAttribute("webApplication", webApplication);

	}

	public void start() throws Exception {

		if (!server.getState().equals(AbstractLifeCycle.STARTED)) {
			server.start();
		}
	}

	public void stop() throws Exception {

		if (server.getState().equals(AbstractLifeCycle.STARTED)) {
			server.stop();
		}
	}

	public String status() throws Exception {
		return server.getState();
	}

}
