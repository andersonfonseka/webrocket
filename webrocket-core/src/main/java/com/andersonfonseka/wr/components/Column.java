package com.andersonfonseka.wr.components;

import java.util.UUID;

public class Column extends Component {

	private String title;

	private String type;

	private String value;
	
	private String field;
	
	public static final String SELECTION = "selection";
	
	public static final String TEXT = "text";

	public static final String NUMBER = "number";
	
	public static final String DATE = "date";
	
	public Column(String title, String type) {
		super("Column_" + UUID.randomUUID().toString());
		this.title = title;
		this.type = type;
	}
	
	public Column(String title, String type, String fieldName) {
		super("Column_" + UUID.randomUUID().toString());
		this.title = title;
		this.type = type;
		this.field = fieldName;
	}


	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}
	
	public String getField() {
		return field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String doRender() {
		
		StringBuilder sb = new StringBuilder();
		
		if (this.type.equals(Column.TEXT)) {
			sb.append("<td>" + this.value + "</td>");
		} else if (this.type.equals(Column.SELECTION)) {
			sb.append("<td><input type=\"radio\" name=" + this.getParent().getId() + " value="  + this.value +  "></input></td>");
		}
		
		return sb.toString();
	}

}
