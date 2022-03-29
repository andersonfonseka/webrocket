package com.andersonfonseka.wr.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.andersonfonseka.wr.model.WebEntity;
import com.andersonfonseka.wr.validator.FormValidator;
import com.andersonfonseka.wr.validator.ValidatorMessage;

public class WebForm extends Component {
	
	private List<ValidatorMessage> messages = new ArrayList<ValidatorMessage>();
	
	private String title = "";
	
	private String subtitle = "";
	
	private List<FormValidator> validators = new ArrayList<FormValidator>();
	
	public WebForm(String title) {
		super("WebForm#" + UUID.randomUUID().toString());
		this.title = title;
	}
	
	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public void clearMessages() {
		this.messages.clear();
	}
	
	public List<ValidatorMessage> getMessages() {
		return messages;
	}
	
	public void addValidatorMessage(ValidatorMessage validatorMessage) {
		if (validatorMessage.isResult()) {
			this.messages.add(validatorMessage);
		}
	}
	
	public void addValidator(FormValidator validator) {
		this.validators.add(validator);
	}
	
	public List<FormValidator> getValidators() {
		return validators;
	}

	public void validate(Map<String, String[]> params) {
		
		if (this.getMessages().isEmpty()) {
			for (FormValidator validator : validators) {
				
				if (this.getParent() instanceof WebPage) {
					WebPage page = (WebPage) this.getParent();
					validator.setWebApplication(page.getWebApplication());
				}
				
				addValidatorMessage(validator.doValidate(params));
			}
		}

	}
	
	@Override
	public String doRender() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<h3>" + this.title + "</h3>");
		sb.append("<small>" + this.subtitle + "</small>");

		if (!this.messages.isEmpty()) {
			
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
		}
		
		sb.append("<form method=POST>");		

			sb.append("<div class=\"bd-example\">");		
			
				sb.append("<input type=\"hidden\" name=\"page\" value=" + this.getParent().getId() +">");
				sb.append("<input type=\"hidden\" name=\"form\" value=" + this.getId() +">");
				sb.append("<input type=\"hidden\" id=\"submitId\" name=\"submitId\">");
				
				for (Component component : super.getComponents()) {
					sb.append(component.doRender());
				}
	
			sb.append("</div>");
		
		sb.append("</form>");
		
				
		return sb.toString();		
	}

}
