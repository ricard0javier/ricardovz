package com.ricardovz.api.service;

import java.util.List;
import java.util.Map;

import com.ricardovz.api.dto.PropertyDTO;

/**
 * Interface used to define the behaviour of a properties services in order to
 * provide a template the operations available
 * 
 * @author Ricardo Javier
 *
 */
public interface PropertiesService {

    /**
     * Returns a Map of properties where the keys are the founded propertyNames
     * and the values their respective value
     * 
     * @param propertyNames
     * @return Map<String, String>
     */
    public Map<String, String> getProperties(List<String> propertyNames);

    /**
     * Creates or updated the properties passed as parameter
     * 
     * @param properties
     */
    public void setProperties(List<PropertyDTO> properties);

    /**
     * Deletes the properties that match the provided property names passed as
     * parameter
     * 
     * @param propertyNames
     */
    public void deleteProperties(List<String> propertyNames);

}
