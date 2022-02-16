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
	
	public WebPage(String title) {
		super("WebPage_" + UUID.randomUUID().toString());
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
	
	public WebApplication getWebApplication() {
		return webApplication;
	}

	public void setWebApplication(WebApplication webApplication) {
		this.webApplication = webApplication;
		message = this.webApplication.getMessageBundle().getBundle(this.webApplication.getDefaultMessageBundle());
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

	public abstract void onLoad(Map<String, String[]> params);

	public String doRender() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<head>");
		
		sb.append("<!-- Latest compiled and minified CSS -->\r\n"
				+ "<link href=\"https://getbootstrap.com/docs/5.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");

		sb.append("<!-- Latest compiled and minified CSS -->\r\n"
				+ "<link rel=\"stylesheet\" href=\"https://getbootstrap.com/docs/5.1/assets/css/docs.css\">");

		
		sb.append("</head>");

		sb.append("<body>");
		
		sb.append("<div class=\"container-fluid bs-docs-container\">");
		
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

		sb.append("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\" crossorigin=\"anonymous\"></script>");
		
		sb.append("</body>");
		
		return sb.toString();
	}
	
	public abstract WebForm createForm();
	
}
