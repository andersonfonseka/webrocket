package com.andersonfonseka.wr.components;

import java.util.UUID;

public class Image extends Component {

	private String src;
	
	public Image(String src) {
		super("Image#" + UUID.randomUUID().toString());
		this.src = src;
	}

	@Override
	public String doRender() {

		StringBuilder sb = new StringBuilder();
		
		sb.append("<img src=" + this.src +  " class=\"img-thumbnail\" alt=\"...\">");
		
		return sb.toString();
	}
	
}
