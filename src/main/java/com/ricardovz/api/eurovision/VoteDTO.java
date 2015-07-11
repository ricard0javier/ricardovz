package com.ricardovz.api.eurovision;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Represents a vote DTO used to vote in the Eurovision contests
 * Created by ricardo on 23/05/2015.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VoteDTO {

    /**
     * User identification
     */
    private String userID;

    /**
     * Votes in order of points
     */
    private List<String> contestantVoted;
}
