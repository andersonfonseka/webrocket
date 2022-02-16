package com.andersonfonseka.wr.components;

import java.util.UUID;

import com.andersonfonseka.wr.style.Color;

public class NavBar extends Component {
	
	private String title;
	
	private String style = Color.LIGHT;

	public NavBar(String title) {
		super("NavBar_" + UUID.randomUUID().toString());
		this.title = title;
	}
	
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@Override
	public String doRender() {

		StringBuilder sb = new StringBuilder();

		sb.append("<nav class=\"navbar navbar-light bg-" + this.style + "\">");
		sb.append("<div class=\"container-fluid\">");
		sb.append("<a class=\"navbar-brand\" href=\"#\">");
		//sb.append("<img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Bootstrap_logo.svg/1280px-Bootstrap_logo.svg.png\" alt=\"\" width=\"30\" height=\"24\">");
		sb.append(this.title);
		sb.append("</a></div></nav><br/>");

		return sb.toString();
	}

}
