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

		sb.append("<div class=\"modal\" tabindex=\"-1\" style=\"display: block;\">");
		sb.append("<div class=\"modal-dialog\">");
		sb.append("<div class=\"modal-content\">");
		sb.append("<div class=\"modal-header\">");
		sb.append("<h5 class=\"modal-title\">Message Dialog</h5>");
		sb.append(
				"<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		sb.append("</div>");
		sb.append("<div class=\"modal-body\">");

			sb.append("<ul style=\"margin-top:0; margin-bottom:0;\">");
			for (ValidatorMessage validatorMessage : super.getMessages()) {
			sb.append("<li>");
			sb.append(validatorMessage.getMessage());
			sb.append("</li>");
			}
			sb.append("</ul>");
		
		sb.append("</div>");
		sb.append("<div class=\"modal-footer\">");
		
		for (Component component : super.getComponents()) {
			sb.append(component.doRender());
		}

		sb.append("     </div>\r\n"
				+ "    </div>\r\n"
				+ "  </div>\r\n"
				+ "</div>");
		

		return sb.toString();

	}

	public abstract Button confirm();

	public abstract Button cancel();

}
