package com.ricardovz.api.eurovision.vo;

import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * Represents a Value Object instance of a addVote to be mapped in the dataabase
 *
 * Created by ricardo on 23/05/2015.
 */
@Value
@Document(collection = "eurovisionVotes")
public class VoteVO {

    /**
     * Identification of the addVote
     */
    private String id;

    /**
     * Identification of the user who owes the addVote
     */
    private String userId;

    /**
     * Votes to be saved represented as a Map of contestantId and addVote entries
     */
    private Map<String, Integer> votes;
}
