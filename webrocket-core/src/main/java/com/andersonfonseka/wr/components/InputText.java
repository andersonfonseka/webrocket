package com.andersonfonseka.wr.components;

public class InputText extends Input {

	private String name;

	private String type;

	private int maxLength = 28;

	private int size = 30;

	public static final String HIDDEN = "hidden";
	
	public static final String TEXT = "text";

	public static final String NUMBER = "number";

	public static final String DATE = "date";
	
	public static final String PASSWORD = "password";
	
	public InputText(String id, String type) {
		super(id);
		this.type = type;
	}

	public InputText(String id, String type, String placeholder) {
		super(id);
		this.type = type;
		super.setPlaceholder(placeholder);
	}



	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	@Override
	public String doRender() {
		
		StringBuilder sb = new StringBuilder();
		
		if (this.type.equals(HIDDEN)) {
			sb.append("<input type=" + this.type + " id=" + this.getId() + " name=" + this.getId() + " value=" + this.getValue() + ">");
		} else {
			sb.append("<input type=" + this.type + " id=" + this.getId() + " name=" + this.getId() + " class=\"form-control\" maxLength=" + this.maxLength 
			+ " placeholder=\'" + super.getPlaceholder() + "'\" value='" + this.getValue() + "'\">"
			+ "<label for=" + this.getId() + ">" + this.getPlaceholder() + "</label>");
		}
		
		return sb.toString();
	}

}
