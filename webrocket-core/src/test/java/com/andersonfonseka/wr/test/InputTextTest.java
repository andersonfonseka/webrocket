package com.andersonfonseka.wr.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.andersonfonseka.wr.components.InputText;

class InputTextTest {

	@Test
	void testDoRender() {
		
		InputText txInput = new InputText("txName", InputText.TEXT, "Type your name here");
		txInput.setValue("Anderson Fonseca");
		
		assertTrue(txInput.doRender().length() > 0);
	}

	@Test
	void testInputTextStringStringString() {
		
		InputText txInput = new InputText("txName", InputText.TEXT, "Type your name here");
		txInput.setValue("Anderson Fonseca");
		
		assertTrue(txInput.getValue().equals("Anderson Fonseca"));
	}

}
