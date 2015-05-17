package com.ricardovz.api.vo;

import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Value Object for receipts
 * <p/>
 * Created by ricardo on 04/05/2015.
 */
@Value
@Document(collection = "receipts")
public class ReceiptVO {

    /**
     * Unique identifier of the object
     */
    private String id;

    /**
     * User Id that created the receipt
     */
    private String userId;

    /**
     * Receipt amount
     */
    private Double amount;

    /**
     * Receipt Date
     */
    private Date date;

    /**
     * Path to the image
     */
    private String imagePath;

    /**
     * Deleted mark
     */
    private Boolean deleted;

}
