package com.andersonfonseka.wr.components;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

public class DataGrid extends Input {
	
	private Collection<?> data;
	
	public DataGrid(String id, Collection<?> data) {
		super(id);
		this.data = data;
	}

	public Column getColumnById(int pos) {
		return (Column) this.getComponents().get(pos);
	}
	
	@Override
	public String doRender() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<table class=\"table\">");
		
		sb.append("<thead>");
		
		for(Component comp: getComponents()) {
			Column column = (Column) comp;
			sb.append("<th scope=\"col\">" + column.getTitle() + "</th>");
		}
		
		sb.append("</thead>");
		
		sb.append("<tbody>");

		Iterator<?> it = data.iterator();
		
		while (it.hasNext()) {
			
			sb.append("<tr>");

			Object obj = it.next();
			
			for (int j = 0; j < getComponents().size(); j++) {
				
				Column col = getColumnById(j);
				
						try {
							
							Method m = obj.getClass().getMethod(col.getField(), (Class<?>[]) null);
							if (null != m) {
								col.setValue(String.valueOf(m.invoke(obj, (Object[]) null)));
							}
							
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						
				
				
				sb.append(col.doRender());
			}
			
			sb.append("</tr>");
		}
		
		sb.append("</tbody>");

		
		sb.append("</table>");
		
		return sb.toString();
	}

}
