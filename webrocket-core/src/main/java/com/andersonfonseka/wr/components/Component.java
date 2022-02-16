package com.andersonfonseka.wr.components;

import java.util.ArrayList;
import java.util.List;

public abstract class Component {

	private String id;
	
	private Component parent;
	
	private List<Component> components = new ArrayList<Component>();
	
	public Component(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public void add(Component component) throws RuntimeException {
		
		Component comp = getComponentById(component.getId(), this);
		
		if (null == comp) {
			component.setParent(this);
			this.components.add(component);
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

	private Component compFound = null; 
	
	public Component getComponentById(String id) {
		this.compFound = null;
		this.getComponentById(id, this);
		return compFound;
	}
 	
	public Component getComponentById(String id, Component component) {
		
		for (Component comp : component.getComponents()) {
			if (comp.getId().equals(id)){
				if (null == compFound) {
					compFound = comp;
					break;
				}
			} else {
				getComponentById(id, comp);
			}
		}
		return compFound;
	}

	public abstract String doRender();

}
