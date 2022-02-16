package com.andersonfonseka.wr.validator;

import java.util.Map;

public abstract class FormValidator extends Validator {

	@Override
	public ValidatorMessage doValidate() {
		return null;
	}

	public abstract  ValidatorMessage doValidate(Map<String, String[]> params);

}
