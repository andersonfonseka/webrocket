package com.andersonfonseka.wr.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.andersonfonseka.wr.components.WebForm;

class WebFormTest {

	@Test
	void testDoRender() {
		
		WebForm wp = new WebForm("frmTeste", "Teste Web Form");
		
		assertTrue(wp.doRender().length() > 0);
		
	}

	@Test
	void testWebPage() {
		
		WebForm wp = new WebForm("frmTeste", "Teste Web Form");
		
		assertTrue(wp.getId().length() > 0);
	}

}
