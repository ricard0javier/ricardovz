package com.ricardovz.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.ricardovz.api.dto.PropertyDTO;
import com.ricardovz.api.repository.PropertiesRepository;

/**
 * Implementation of the PropertiesService interface operations
 * 
 * @author Ricardo Javier
 *
 */
@Service
@Slf4j
public class PropertiesServiceImpl implements PropertiesService {

    private PropertiesRepository repository;

    @Autowired
    public PropertiesServiceImpl(PropertiesRepository propertiesRepository) {
	
	log.trace("Defining the service dependencies");
	
	this.repository = propertiesRepository;
    }

    @Override
    public Map<String, String> getProperties(List<String> propertyNames) {

	log.trace("Retrieving the properties values as a Map for '{}'", propertyNames);
	
	List<PropertyDTO> properties = repository.findByNames(propertyNames);

	Map<String, String> propertiesMap = null;
	if (!properties.isEmpty()) {
	    propertiesMap = new HashMap<String, String>();
	    for (PropertyDTO propertyDTO : properties) {
		propertiesMap.put(propertyDTO.getName(), propertyDTO.getValue());
	    }
	}

	return propertiesMap;
    }

    @Override
    @Transactional
    public void setProperties(List<PropertyDTO> properties) {

	log.trace("Creating or updating the properties '{}'", properties);
	
	List<String> propertyNames = Lists.transform(properties, new Function<PropertyDTO, String>() {

	    @Override
	    public String apply(PropertyDTO input) {
		return input.getName();
	    }

	});

	repository.deleteByNames(propertyNames);
	repository.save(properties);

    }

    @Override
    @Transactional
    public void deleteProperties(List<String> propertyNames) {

	log.trace("Removing the properties '{}'", propertyNames);
	
	repository.deleteByNames(propertyNames);
    }

}
