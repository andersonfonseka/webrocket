package com.andersonfonseka.wr.message;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.andersonfonseka.wr.components.Component;
import com.andersonfonseka.wr.validator.ValidatorMessage;

public class MessageDialog extends Component {
	
	private List<ValidatorMessage> messages = new ArrayList<ValidatorMessage>();

	public MessageDialog(List<ValidatorMessage> messages) {
		super("MessageDialog#" + UUID.randomUUID().toString());
		this.messages = messages;
	}
	
	public List<ValidatorMessage> getMessages() {
		return messages;
	}

	@Override
	public String doRender() {
		
		StringBuilder sb = new StringBuilder();

		sb.append("<div class=\"bd-example\">");
		sb.append("<div class=\"alert alert-warning\" role=\"alert\">");
		sb.append("<ul style=\"margin-top:0; margin-bottom:0;\">");

		for (ValidatorMessage validatorMessage : messages) {
			sb.append("<li>");
			sb.append(validatorMessage.getMessage());
			sb.append("</li>");
		}

		sb.append("</ul>");
		sb.append("</div>");
		sb.append("</div>");

		return sb.toString();
	}

}
