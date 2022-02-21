package com.andersonfonseka.wr.message;

import java.util.List;

import com.andersonfonseka.wr.components.Button;
import com.andersonfonseka.wr.components.Component;
import com.andersonfonseka.wr.validator.ValidatorMessage;

public abstract class MessageDialogConfirmation extends MessageDialog {

	public MessageDialogConfirmation(List<ValidatorMessage> messages) {
		super(messages);
		add(confirm());
		add(cancel());
	}
	
	@Override
	public String doRender() {

		StringBuilder sb = new StringBuilder();
		

		sb.append("<div class=\"alert alert-warning\" role=\"alert\">");
		sb.append("<ul style=\"margin-top:0; margin-bottom:0;\">");

		for (ValidatorMessage validatorMessage : super.getMessages()) {
			sb.append("<li>");
			sb.append(validatorMessage.getMessage());
			sb.append("</li>");
		}

		sb.append("</ul>");
		
		sb.append("<div class=\"col form-floating mb-3\">");
		

		sb.append("</div>");
		
		sb.append("</div>");
	
		for (Component component : super.getComponents()) {
			sb.append(component.doRender());
		}
		
		
		return sb.toString();
		
	}



	public abstract Button confirm();
	
	public abstract Button cancel();
	
}
