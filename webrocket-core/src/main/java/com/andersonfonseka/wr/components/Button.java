package com.andersonfonseka.wr.components;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.andersonfonseka.wr.action.WebAction;
import com.andersonfonseka.wr.style.Color;

public class Button extends Component {
	
	private String title;
	
	public static final String SUBMIT = "submit";

	public static final String BUTTON = "button";
	
	public static final String MODAL = "modal";
	
	public static final String CANCEL = "button";

	private Map<String, String[]> params = new HashMap<String, String[]>();
	
	private String type = SUBMIT;
	
	private WebAction webAction;
	
	private boolean immediate;
	
	private String style = Color.PRIMARY;

	
	public Button(String id, String title, String type) {
		super(id);
		this.title = title;
		this.type = type;
	}

	public Button(String id, String title, WebAction webAction) {
		super(id);
		this.title = title;
		this.webAction = webAction;
	}
	
	public Button(String id, String title, String type, WebAction webAction) {
		super(id);
		this.title = title;
		this.type = type;
		this.webAction = webAction;
	}
	
	public Button(String id, String title, String type, WebAction webAction, boolean immediate) {
		super(id);
		this.title = title;
		this.type = type;
		this.webAction = webAction;
		this.immediate = immediate;
	}
	
	public Button(String id, String title, String type, WebAction webAction, boolean immediate, String style) {
		super(id);
		this.title = title;
		this.type = type;
		this.webAction = webAction;
		this.immediate = immediate;
		this.style = style;
	}
	
	public Button(String title, String type, WebAction webAction, boolean immediate) {
		super("Button#" + UUID.randomUUID().toString());
		this.title = title;
		this.type = type;
		this.webAction = webAction;
		this.immediate = immediate;
	}

	public WebAction getWebAction() {
		return webAction;
	}

	public boolean isImmediate() {
		return immediate;
	}
	
	public Map<String, String[]> getParams() {
		return params;
	}

	public void addParam(String key, String value) {
		this.params.put(key, new String[] {value});
	}

	@Override
	public String doRender() {
		
		StringBuilder sb = new StringBuilder();
		
		if (this.type.equals(Button.MODAL)) {
			
			sb.append("<button type=\"button\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#staticBackdrop\">\r\n"
					+ this.title
					+ "</button>");
			
		} else {
			
			sb.append("<button id=" + this.getId() + " type=" + this.type +" class=\"btn btn-" + this.style + " mb-3\" onclick=\"setSubmit(this.id);\">" + this.title + "</button>");
		}
		
		return sb.toString();
	}


}
