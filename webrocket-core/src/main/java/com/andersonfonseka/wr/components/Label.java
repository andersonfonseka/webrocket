package com.andersonfonseka.wr.components;

import java.util.UUID;

public class Label extends Component {

	private String text;
	
	public Label(String text) {
		super("Label#" + UUID.randomUUID().toString());
		this.text = text;
	}

	@Override
	public String doRender() {
		return "<span class=\"label label-default\">" + this.text + "</span>";
	}

}
