package com.ricardovz.api.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

/**
 * Entity representation of a property
 * 
 * @author Ricardo Javier
 *
 */
@Data
@Entity(name = "properties")
public class PropertyDTO {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String value;

    /**
     * Default constructor
     */
    public PropertyDTO() {
    }

    /**
     * Construct the object based on the parameters received
     * 
     * @param name
     * @param value
     */
    public PropertyDTO(String name, String value) {
	this.name = name;
	this.value = value;
    }

}
