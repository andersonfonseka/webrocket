package com.andersonfonseka.wr.components;

import java.util.Map;
import java.util.UUID;

import com.andersonfonseka.wr.application.WebApplication;
import com.andersonfonseka.wr.message.Message;

public abstract class WebPage extends Component {

	private WebApplication webApplication;
	
	private Message message;
	
	private String title;
	
	private boolean blnOnLoad = false;
	
	private Map<String, String[]> params;
	
	private NavBar navBar;
	
	public static int NEW = 1;
	
	public static int CREATED = 2;
	
	private int status;
	
	public static int CONTAINER = 1;
	
	public static int CONTAINER_FLUID = 2;
	
	private int container = 2;
	
	public WebPage() {
		super("WebPage_" + UUID.randomUUID().toString());
	}

	public WebPage(String title) {
		super("WebPage_" + UUID.randomUUID().toString());
		this.title = title;
	}
	
	public WebPage(String title, int container) {
		super("WebPage_" + UUID.randomUUID().toString());
		this.title = title;
		this.container = container;
	}

	public String getTitle() {
		return title;
	}
	
	public WebApplication getWebApplication() {
		return webApplication;
	}

	public void setWebApplication(WebApplication webApplication) {
		this.webApplication = webApplication;
		
		if (null != this.webApplication.getMessageBundle()) {
			message = this.webApplication.getMessageBundle().getBundle(this.webApplication.getDefaultMessageBundle());
		}
	}
	
	public Message getMessageBundle() {
		return message;
	}
	
	public boolean isBlnOnLoad() {
		return blnOnLoad;
	}

	public void setBlnOnLoad(boolean blnOnLoad) {
		this.blnOnLoad = blnOnLoad;
	}

	public void setNavBar(NavBar navBar) {
		this.navBar = navBar;
	}
	
	public Map<String, String[]> getParams() {
		return params;
	}

	public void setParams(Map<String, String[]> params) {
		this.params = params;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public abstract void onLoad(Map<String, String[]> params);

	public String doRender() {
		
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<head>");
		
		sb.append("<!-- Latest compiled and minified CSS -->\r\n"
				+ "<link href=\"" + this.webApplication.getContext()  + "?op=css/bootstrap.min.css\" rel=\"stylesheet\">");

		sb.append("<!-- Latest compiled and minified CSS -->\r\n"
				+ "<link rel=\"stylesheet\" href=\"" + this.webApplication.getContext() + "?op=css/docs.css\">");

		sb.append("<script src=\"" + this.webApplication.getContext() + "?op=js/bootstrap.bundle.min.js\"></script>");
		sb.append("<script src=\"" + this.webApplication.getContext() + "?op=js/docs.min.js\"></script>");

		sb.append("<title>" + this.webApplication.getTitle() + "</title>");
			
		
		sb.append("</head>");

		sb.append("<body>");
		
		
		if (this.container == 1) {
			sb.append("<div class=\"container bs-docs-container\">");
		} else 	if (this.container == 2) {
			sb.append("<div class=\"container-fluid bs-docs-container\">");
		}

		
		if (null != this.navBar) {
			sb.append(this.navBar.doRender());
		}

		sb.append("<div class=\"page-header\"><h2 class=\"bd-title\" id=\"content\">" + this.getTitle() + "</h2></div>");

		for (Component component : this.getComponents()) {
			sb.append(component.doRender());
		}
		
		sb.append("</div>");
		
		
		sb.append("<script>");
		sb.append("function setSubmit(id) { document.getElementById('submitId').value = id; }");
		sb.append("</script>");
		
		sb.append("</body>");
		
		return sb.toString();
	}
	
	public abstract WebForm createForm();
	
}
