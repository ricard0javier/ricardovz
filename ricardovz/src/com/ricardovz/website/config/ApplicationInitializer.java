package com.ricardovz.website.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {
	static final Logger logger = LoggerFactory.getLogger(ApplicationInitializer.class);

	@Override
	public void onStartup(ServletContext container) throws ServletException {
	    logger.info("Configurating application from java class");
		ServletRegistration.Dynamic registration = container.addServlet("spring3-mvc", new DispatcherServlet());
		registration.setLoadOnStartup(1);
		registration.addMapping("/");
	}

}
