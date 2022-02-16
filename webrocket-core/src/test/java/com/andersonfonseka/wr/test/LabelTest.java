package com.andersonfonseka.wr.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.andersonfonseka.wr.components.Label;

class LabelTest {

	@Test
	void testDoRender() {
		
		Label txInput = new Label("Hello world");
		assertTrue(txInput.doRender().length() > 0);
	}

	@Test
	void testInputTextStringStringString() {
		
		Label txInput = new Label("Hello world");
		
		assertTrue(txInput.getId().length() > 0);
	}

}
