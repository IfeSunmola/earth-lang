package com.ifesunmola.webserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WebServerApplicationTests {
	@Autowired
	private ApplicationContext appContext;

	@Test
	void contextLoads() {
		// This test will pass if the application context loads successfully
		// and all beans are created without any issues.
		// You can add more specific assertions if needed.
		assertNotNull(appContext, "Application context should" + " not be null");
	}

	@Test
	void beansLoaded() {
		assertNotNull(appContext.getBean(PlaygroundController.class),
			"PlaygroundController bean should be loaded in the application context");

		assertNotNull(appContext.getBean(ExceptionHandlers.class),
			"ExceptionHandlers bean should be loaded in the application context");
	}
}
