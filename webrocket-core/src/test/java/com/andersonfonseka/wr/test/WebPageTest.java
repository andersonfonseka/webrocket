package com.andersonfonseka.wr.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.andersonfonseka.wr.components.WebForm;
import com.andersonfonseka.wr.components.WebPage;

class WebPageTest {

	@Test
	void testDoRender() {
		
		WebPage wp = new WebPage("WpTest") {
			
			@Override
			public void onLoad(Map<String, String[]> params) {}
			
			@Override
			public WebForm createForm() {
				return null;
			}
		};
		
		assertTrue(wp.doRender().length() > 0);
		
	}

	@Test
	void testWebPage() {
		
		WebPage wp = new WebPage("WpTest") {
			
			@Override
			public void onLoad(Map<String, String[]> params) {}
			
			@Override
			public WebForm createForm() {
				return null;
			}
		};
		
		assertTrue(wp.getTitle().length() > 0);
	}

}
