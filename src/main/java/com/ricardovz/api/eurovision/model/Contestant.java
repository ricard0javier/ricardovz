package com.ricardovz.api.eurovision.model;

import lombok.Data;

/**
 * Represent an Eurovision contestant
 *
 * Created by ricardo on 23/05/2015.
 */
@Data
public class Contestant {

    /**
     * identification of the contestant
     */
    private String id;

    /**
     * Contestant name
     */
    private String name;

    /**
     * Points gained
     */
    private Integer points;

    /**
     * Constructor
     * @param name name of the contestant
     */
    public Contestant(String id, String name) {

        this.id = id;
        this.name = name;
    }
}
