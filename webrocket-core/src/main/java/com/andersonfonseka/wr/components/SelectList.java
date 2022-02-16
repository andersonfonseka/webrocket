package com.andersonfonseka.wr.components;

public class SelectList extends Input {
	
	public SelectList(String id, String placeholder) {
		super(id);
		super.setPlaceholder(placeholder);
		
		if (super.getPlaceholder().length() > 0) {
			this.add(new SelectItem(super.getPlaceholder(), "", true, true));
		}

	}


	@Override
	public String doRender() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<select id=" + this.getId() + " name=" + this.getId() + " class=\"form-select\" >");
		
		
		for (Component selectItem : getComponents()) {
			
			SelectItem item = (SelectItem) selectItem;
			item.setSelected(item.getValue().equals(this.getValue()));
			sb.append(item.doRender());
		}
		
		sb.append("</select>");
		sb.append("<label for=" + this.getId() + ">" + this.getPlaceholder() + "</label>");
		
		return sb.toString();
	}
	
	

}
