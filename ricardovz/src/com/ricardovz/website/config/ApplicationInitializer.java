package com.ricardovz.website.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		ServletRegistration.Dynamic registration = container.addServlet("spring3-mvc", new DispatcherServlet());
		registration.setLoadOnStartup(1);
		registration.addMapping("/");
	}

}
