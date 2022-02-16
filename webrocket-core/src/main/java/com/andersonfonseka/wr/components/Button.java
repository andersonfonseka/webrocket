package com.andersonfonseka.wr.components;

import com.andersonfonseka.wr.action.WebAction;
import com.andersonfonseka.wr.style.Color;

public class Button extends Component {
	
	private String title;
	
	private String type;
	
	private WebAction webAction;
	
	private boolean immediate;
	
	private String style = Color.PRIMARY;

	public static final String SUBMIT = "submit";

	public static final String BUTTON = "button";
	
	public static final String CANCEL = "button";
	
	public Button(String id, String title, String type) {
		super(id);
		this.title = title;
		this.type = type;
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

	public WebAction getWebAction() {
		return webAction;
	}

	public boolean isImmediate() {
		return immediate;
	}

	@Override
	public String doRender() {
		return "<button id=" + this.getId() + " type=" + this.type +" class=\"btn btn-" + this.style + " mb-3\" onclick=\"setSubmit(this.id);\">" + this.title + "</button>";
	}

}
