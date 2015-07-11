package com.ricardovz.api.dto;

/**
 * Class that represents the data contained in a information Message
 * Created by ricardo on 09/07/2015.
 */

import lombok.Data;

import java.util.Calendar;

@Data
public class InformationMessageDTO {

    /**
     * Subject of the message
     */
    private String subject;

    /**
     * URL to the web representation of the message
     */
    private String url;

    /**
     * Calendar instance
     */
    private Calendar calendar;

    /**
     * Unsubscription URL
     */
    private String unsubscribeUrl;

    /**
     * Message brief description
     */
    private String messageTeaser;

    /**
     * Message tittle
     */
    private String title;

    /**
     * Message subtitle
     */
    private String subtitle;

    /**
     * Message body
     */
    private String messageBody;



}
