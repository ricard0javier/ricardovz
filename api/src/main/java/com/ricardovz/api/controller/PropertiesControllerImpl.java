package com.ricardovz.api.controller;

import java.util.Arrays;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Joiner;
import com.ricardovz.api.dto.PropertyDTO;
import com.ricardovz.api.service.PropertiesService;

/**
 * Implementation of the PropertiesController interface in order to provide the
 * end-points for the management of properties
 * 
 * @author Ricardo Javier
 *
 */
@Slf4j
@RestController
@RequestMapping("/properties")
public class PropertiesControllerImpl implements PropertiesController {

    private PropertiesService service;

    @Autowired
    public PropertiesControllerImpl(PropertiesService propertiesService) {

	log.trace("Setting the PropertiesController dependencies");

	this.service = propertiesService;
    }

    @Override
    @RequestMapping(value = "/getProperties", method = RequestMethod.GET)
    public Map<String, String> getProperties(String[] propertyNames) {

	log.info("Getting the properties '{}'", Arrays.toString(propertyNames));

	return service.getProperties(Arrays.asList(propertyNames));
    }

    @Override
    @RequestMapping(value = "/setProperties", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void setProperties(@RequestBody PropertyDTO[] properties) {

	log.info("Setting the properties '{}'", Joiner.on("; ").join(properties));

	service.setProperties(Arrays.asList(properties));

    }

    @Override
    @RequestMapping(value = "/deleteProperties", method = RequestMethod.DELETE)
    public void deleteProperties(String[] propertyNames) {

	log.info("Deleting the properties '{}'", Arrays.toString(propertyNames));

	service.deleteProperties(Arrays.asList(propertyNames));
    }
}
