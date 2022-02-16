package com.andersonfonseka.wr.components;

public class Card extends Component {

	private String title = "";

	private String subTitle = "";

	public Card(String id) {
		super(id);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	@Override
	public String doRender() {

		StringBuilder sb = new StringBuilder();

		sb.append("<div class=\"card\">");
		sb.append("<div class=\"card-body\">");
		sb.append("<h5 class=\"card-title\">" + this.title + "</h5>");
		sb.append("<p class=\"card-text\">" + this.subTitle + "</p>");

		for (Component component : super.getComponents()) {
			sb.append(component.doRender());
		}

		sb.append("</div>");
		sb.append("</div>");

		return sb.toString();
	}

}
