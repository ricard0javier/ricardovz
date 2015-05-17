package com.ricardovz.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Receipt Data Transfer Object
 * <p/>
 * Created by ricardo on 27/04/2015.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceiptDTO {

    /**
     * Receipt id
     */
    private String id;

    /**
     * User identification
     */
    private String userId;

    /**
     * User name
     */
    private String userName;

    /**
     * Receipt amount
     */
    private Double amount;

    /**
     * Receipt Date
     */
    private String date;

    /**
     * Does it has image
     */
    private boolean hasImage;
}
