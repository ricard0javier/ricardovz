package com.ricardovz.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Data Transfer Object representation of a user
 * <p/>
 * Created by ricardo on 04/05/2015.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    /**
     * User identification
     */
    private String id;

    /**
     * User name
     */
    private String name;
}
