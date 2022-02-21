package com.andersonfonseka.wr.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public abstract class Component {

	private String id;
	
	private Component parent;
	
	private List<Component> components = new ArrayList<Component>();
	
	private Map<String, Component> componentMap = new HashMap<String, Component>();
	
	public Component(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public void add(Component component) throws RuntimeException {
		
		Component comp = this.componentMap.get(id);
		
		if (null == comp) {
			component.setParent(this);
			this.components.add(component);
			this.componentMap.put(component.getId(), component);
		} else {
			throw new RuntimeException("There's a component with same Id already.");
		}
	}
	
	public List<Component> getComponents() {
		return components;
	}
	
	public Component getParent() {
		return parent;
	}

	public void setParent(Component parent) {
		this.parent = parent;
	}

	
	public Component getComponentById(String id) {
		
		Component component = this.componentMap.get(id);

		if (component == null) {
			
			for(Component comp : this.componentMap.values()) {
				component = comp.getComponentById(id);
				
				if (component != null) {
					Logger.getAnonymousLogger().info(component.toString());
					break;
				}
			}
			
		}
		
		return component;
	}

	public abstract String doRender();

	@Override
	public String toString() {
		return "Component [id=" + id + "]";
	}
	
	
	
	

}
