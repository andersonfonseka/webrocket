package com.andersonfonseka.wr.validator;

public class ValidatorMessage {

	private String message;

	private boolean result = false;

	public ValidatorMessage(String message) {
		super();
		this.message = message;
	}

	public ValidatorMessage(boolean result) {
		super();
		this.result = result;
	}

	public ValidatorMessage(String message, boolean result) {
		super();
		this.message = message;
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}
