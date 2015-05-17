package com.ricardovz.api.vo;

import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User Value object representation
 * <p/>
 * Created by ricardo on 04/05/2015.
 */
@Value
@Document(collection = "users")
public class UserVO {

    /**
     * Identification of the user
     */
    private String id;

    /**
     * Name of the user
     */
    private String name;

    /**
     * Logically deleted
     */
    private Boolean deleted;

}
