package com.andersonfonseka.wr.components;

import java.util.ArrayList;
import java.util.List;

import com.andersonfonseka.wr.application.WebApplication;
import com.andersonfonseka.wr.style.Color;
import com.andersonfonseka.wr.validator.Validator;

public class Input extends Component {

	private String placeholder;
	
	private String value = "";
	
	private Color style;
	
	private List<Validator> validators = new ArrayList<Validator>();
	
	public Input(String id) {
		super(id);
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
		
	public Color getStyle() {
		return style;
	}

	public void setStyle(Color style) {
		this.style = style;
	}

	public void addValidator(Validator validator) {
		validator.setComponent(this);
		this.validators.add(validator);
	}
	
	@Override
	public String doRender() {
		return null;
	}

	public void validate(WebApplication webApplication) {

		WebForm webForm = getWebForm(this);
		
		for (Validator validator : validators) {
			validator.setWebApplication(webApplication);
			webForm.addValidatorMessage(validator.doValidate());
		}
	}
	
	private WebForm _parent;
	
	private WebForm getWebForm(Component component) {
		
		if (component.getParent() instanceof WebForm) {
			_parent = (WebForm) component.getParent();
		} else {
			getWebForm(component.getParent());
		}
		return _parent;
		
	}

}
