package com.andersonfonseka.wr.components;

import java.util.UUID;

public class SelectItem extends Component {
	
	private String label;
	
	private String value;
	
	private boolean selected = false;
	
	private boolean disabled = false;
	
	
	public SelectItem(String label, String value) {
		super("SelectItem#" + UUID.randomUUID().toString());
		this.label = label;
		this.value = value;
	}
	
	public SelectItem(String label, String value, boolean selected) {
		super("SelectItem_" + UUID.randomUUID().toString());
		this.label = label;
		this.value = value;
		this.selected = selected;
	}
	
	public SelectItem(String label, String value, boolean selected, boolean disabled) {
		super("SelectItem_" + UUID.randomUUID().toString());
		this.label = label;
		this.value = value;
		this.selected = selected;
		this.disabled = disabled;
	}

	public String getLabel() {
		return label;
	}

	public String getValue() {
		return value;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String doRender() {
		
		StringBuilder sb = new StringBuilder();
		
		if (this.isSelected() && this.disabled) {
			sb.append("<option selected disabled hidden>" + this.label + "</option>");
		} else if (this.isSelected() && !this.disabled) {
				sb.append("<option value=" + this.value + " selected>" + this.label + "</option>");
		} else {
			sb.append("<option value=" + this.value + ">" + this.label + "</option>");
		}
		
		return sb.toString();
	}
	
}
