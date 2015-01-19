package com.ricardovz.api.controller;

import java.util.Map;

import com.ricardovz.api.dto.PropertyDTO;

/**
 * Defines a set of end-points operations provided by the API and closely related
 * with the management of properties
 * 
 * @author Ricardo Javier
 *
 */
public interface PropertiesController {

    /**
     * Returns a Map of properties that match the property names provided, the
     * map keys are the property names and the values their respective values
     * 
     * @param propertyNames
     * @return Map<String, String>
     */
    Map<String, String> getProperties(String[] propertyNames);

    /**
     * Creates or updates the given properties
     * 
     * @param properties
     */
    void setProperties(PropertyDTO[] properties);

    /**
     * Deletes the properties that match the property names provided
     * @param propertyNames
     */
    void deleteProperties(String[] propertyNames);

}
