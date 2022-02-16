package com.andersonfonseka.wr.validator;

import java.text.MessageFormat;

import com.andersonfonseka.wr.components.Input;

public class RequiredValidator extends Validator {
	
	private String message = "";
	
	public RequiredValidator() {
		super();
	}

	public RequiredValidator(String message) {
		super();
		this.message = message;
	}

	@Override
	public ValidatorMessage doValidate() {

		boolean result = false;
		
		if (this.getComponent() instanceof Input) {

			Input input = (Input) this.getComponent();

			result = (input.getValue().trim() == null);

			if (!result) {
				result = (input.getValue().trim().length() == 0);
			}

			if (message.trim().length() == 0) {
				return new ValidatorMessage(MessageFormat.format(getMessageBundle().getMessage("validator.required"), input.getPlaceholder()), result);
			}
		}

		return new ValidatorMessage(result);
	}

}
