package com.andersonfonseka.wr.components;

import java.util.UUID;

public class Separator extends Component {

	public Separator() {
		super("Separator#" + UUID.randomUUID().toString());
	}

	@Override
	public String doRender() {
		return "<hr/>";
	}

}
