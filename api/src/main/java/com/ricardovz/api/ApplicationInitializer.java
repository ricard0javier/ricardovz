package com.ricardovz.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;

import lombok.extern.slf4j.Slf4j;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.web.WebApplicationInitializer;

import com.ricardovz.api.configuration.SpringConfiguration;

/**
 * Class that starts the configuration of the application
 * 
 * @author Ricardo Javier
 *
 */
@Slf4j
public class ApplicationInitializer implements WebApplicationInitializer {

    private static final String PROPERTIES_FILE = "/com/ricardovz/api/application.properties";
    private static final String SERVLET_NAME = "ricardovz-api";

    @Override
    public void onStartup(ServletContext container) throws ServletException {

	log.info("Starting ricardovz-api servlet");

	Properties properties = getProperties(PROPERTIES_FILE);
	PropertyConfigurator.configure(properties);

	String entryPoint = properties.getProperty("entryPoint");

	log.debug("Configurating application from java class, entry point '{}'", entryPoint);

	HttpServlet dispatcherServlet = SpringConfiguration.getSpringServlet();

	ServletRegistration.Dynamic registration = container.addServlet(SERVLET_NAME, dispatcherServlet);
	registration.addMapping("/" + entryPoint + "/*");
	registration.setLoadOnStartup(1);
    }

    /**
     * Load the properties contained on the file indicated as parameter
     * 
     * @param propertiesFilePath
     *            Path should be absolute to the classpath
     * @return Properties
     */
    private Properties getProperties(String propertiesFilePath) {

	log.trace("getting properties from '{}'", propertiesFilePath);

	Properties defaultProps = new Properties();
	InputStream in = getClass().getResourceAsStream(propertiesFilePath);
	try {
	    defaultProps.load(in);
	    in.close();
	} catch (IOException e) {
	    String msg = String.format("Properties file '%s' not found", propertiesFilePath);
	    log.warn(msg, e);
	}

	return new Properties(defaultProps);
    }

}
