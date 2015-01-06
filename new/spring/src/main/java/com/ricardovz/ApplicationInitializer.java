package com.ricardovz;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;

import com.ricardovz.configuration.SpringConfiguration;

public class ApplicationInitializer implements WebApplicationInitializer {
	
	private static final String ENTRY_POINT = "/api/text/*";

	private static final String SERVLET_NAME = "spring3-mvc";
	
	static final Logger logger = LoggerFactory.getLogger(ApplicationInitializer.class);

	@Override
	public void onStartup(ServletContext container) throws ServletException {
	    logger.info("Configurating application from java class");
	    HttpServlet dispatcherServlet = SpringConfiguration.getSpringServlet();
		ServletRegistration.Dynamic registration = container.addServlet(SERVLET_NAME, dispatcherServlet);
		registration.addMapping(ENTRY_POINT);
		registration.setLoadOnStartup(1);
	}

}
