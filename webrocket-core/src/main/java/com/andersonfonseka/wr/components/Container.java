package com.andersonfonseka.wr.components;

import java.util.UUID;

public class Container extends Component {

	public Container() {
		super("Container_" + UUID.randomUUID().toString());
	}

	@Override
	public String doRender() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class=\"row\" style=\"position: relative; margin-left: 0px; --bs-gutter-x: 0px;\">");
		
		for (Component component : super.getComponents()) {
			sb.append(component.doRender());
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}

}
