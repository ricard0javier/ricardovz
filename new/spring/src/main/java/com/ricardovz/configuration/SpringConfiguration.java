package com.ricardovz.configuration;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("com.ricardovz")
@EnableWebMvc
public class SpringConfiguration extends WebMvcConfigurerAdapter {
	
	public static DispatcherServlet getSpringServlet() {

		DispatcherServlet dispatcherServlet = new DispatcherServlet();
		dispatcherServlet.setContextClass(AnnotationConfigWebApplicationContext.class);
		dispatcherServlet.setContextConfigLocation(SpringConfiguration.class.getCanonicalName());
		
		return dispatcherServlet;
	}
	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter());
	}

}
