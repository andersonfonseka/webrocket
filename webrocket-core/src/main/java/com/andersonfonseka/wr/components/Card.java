package com.andersonfonseka.wr.components;

public class Card extends Component {

	public Card(String id) {
		super(id);
	}

	@Override
	public String doRender() {

		StringBuilder sb = new StringBuilder();

		sb.append("<div class=\"card\">");
		sb.append("<div class=\"card-body\">");
		sb.append("<h5 class=\"card-title\">Special title treatment</h5>");
		sb.append("<p class=\"card-text\">With supporting text below as a natural lead-in to additional content.</p>");
		
		for (Component component : super.getComponents()) {
			sb.append(component.doRender());
		}
		
		sb.append("</div>");
		sb.append("</div>");

		return sb.toString();
	}

}
