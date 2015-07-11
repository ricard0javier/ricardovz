package com.ricardovz.api.vo;

import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Subscriber Value object representation
 * <p/>
 * Created by ricardo on 06/07/2015.
 */
@Value
@Document(collection = "subscribers")
public class SubscriberVO {

    /**
     * Identification of the subscriber
     */
    private String id;

    /**
     * email of the subscriber
     */
    private String email;

    /**
     * Logically deleted
     */
    private Boolean deleted;

}
