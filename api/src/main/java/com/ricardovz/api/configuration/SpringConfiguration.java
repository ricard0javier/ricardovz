package com.ricardovz.api.configuration;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Configuration class used to set the basic MVC spring behaviour and 
 * define the package to be scanned in order to load the spring beans and
 * finally to define the path to access to the application properties 
 * 
 * @author Ricardo Javier
 *
 */
@Configuration
@ComponentScan("com.ricardovz.api")
@EnableWebMvc
@PropertySource("classpath:/com/ricardovz/api/application.properties")
@Slf4j
public class SpringConfiguration extends WebMvcConfigurerAdapter {

    /**
     * Returns a dispatcher servlet configured to use Spring with annotations
     *  
     * @return DispatcherServlet
     */
    public static DispatcherServlet getSpringServlet() {

	log.debug("Configurating the spring servlet and enabling spring annotations");
	
	DispatcherServlet dispatcher = new DispatcherServlet();
	dispatcher.setContextClass(AnnotationConfigWebApplicationContext.class);
	dispatcher.setContextConfigLocation(SpringConfiguration.class.getCanonicalName());

	return dispatcher;
    }

    /**
     * Returns a FormattingConversionService with the default conversion services set
     * 
     * @return FormattingConversionService
     */
    @Bean
    public FormattingConversionService mvcConversionService() {

	log.debug("Adding the default converters to the mvc application");
	
	FormattingConversionService conversionService = new DefaultFormattingConversionService();
	addFormatters(conversionService);
	return conversionService;
    }

    /**
     * Returns a DomainClassConverter with the formating conversion service set
     * 
     * @return DomainClassConverter
     */
    @Bean
    public DomainClassConverter<?> domainClassConverter() {
	
	log.debug("Mapping the the conversion services a domain class processors");
	
	return new DomainClassConverter<FormattingConversionService>(mvcConversionService());
    }

    /**
     * Set the behaviour of the framework when ${} place holders exists on annotations
     * 
     * @return PropertySourcesPlaceholderConfigurer
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	
	log.debug("Allowing the use of ${} as property place holder");
	
	return new PropertySourcesPlaceholderConfigurer();
    }
}
