package com.andersonfonseka.wr.components;

import java.util.UUID;

public class FormGroup extends Component {

	public FormGroup() {
		super("FormGroup#" + UUID.randomUUID().toString());
	}
	
	public FormGroup(String id) {
		super(id);
	}

	@Override
	public String doRender() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class=\"col form-floating mb-3\" style=\"margin-left: 10px;\">");
		
		for (Component component : super.getComponents()) {
			sb.append(component.doRender());
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}

}
