package com.andersonfonseka.wr.components;

import java.util.UUID;

public class Card extends Component {

	private String title = "";

	private String subTitle = "";
	
	private String url = "";

	public Card() {
		super("Card#" + UUID.randomUUID().toString());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		
		if (this.subTitle.length() >= 125) {
			return this.subTitle.substring(0,125) + "...";
		}
		
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String doRender() {

		StringBuilder sb = new StringBuilder();

		sb.append("<div class=\"card\" style=\"width: 9rem;\">");
		
		if (null != this.url && this.url.length() > 0) {
			sb.append("<img src=" + this.url + " class=\"card-img-top\">");
		}
		
		
		sb.append("<div class=\"card-body\">");
		sb.append("<h5 class=\"card-title\">" + this.title + "</h5>");
		sb.append("<p class=\"card-text\">" + getSubTitle() + "</p>");

		for (Component component : super.getComponents()) {
			sb.append(component.doRender());
		}

		sb.append("</div>");
		sb.append("</div>");

		return sb.toString();
	}

}
