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
	
	public void setId(String id) {
		this.id = id;
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

	public void remove(Object component) {
		
		if (this.components.indexOf(component) != -1) {
			this.components.remove(this.components.indexOf(component));
		}
		
	}
	
	public abstract String doRender();

	@Override
	public String toString() {
		return "Component [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Component other = (Component) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}
