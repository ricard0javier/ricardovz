package com.ricardovz.api.eurovision.model;

import com.ricardovz.api.model.User;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an Eurovision Vote and its expected behaviour
 *
 * Created by ricardo on 23/05/2015.
 */
@Data
public class Vote {

    public static final int MAXIMUM_NUMBER_OF_VOTES = 10;
    /**
     * User who owes the Vote
     */
    private User user;

    /**
     * Votes Distribution map
     */
    private Map<Contestant, Integer> votes;

    /**
     * Default constructor
     *
     * @param user {@link User} instance who owes the votes
     * @param votes {@link Map} map of votes
     */
    public Vote(User user, Map<Contestant, Integer> votes) {

        this.user = user;

        if (votes == null) {
            this.votes = new HashMap<>(MAXIMUM_NUMBER_OF_VOTES);
        }
        else {
            this.votes = votes;
        }
    }

    /**
     * Adds or replaces a addVote, if the contestant has been voted it will override its addVote, if the addVote has been used
     * it will be removed
     *
     * @param contestant {@link Contestant} instance to assign the addVote
     * @param vote int value to be assigned
     * @return Current votes
     */
    public Map<Contestant, Integer> addVote(Contestant contestant, int vote) {

        if (contestant == null) {
            throw new IllegalArgumentException("Contestant can't be null");
        }

        if (vote < 1 || vote > 12 || vote == 9 || vote == 11) {
            throw new IllegalArgumentException("vote value is invalid");
        }

        // let's remove the addVote if it is already assigned to other
        for (Map.Entry<Contestant, Integer> currentVote : votes.entrySet()) {
            if (currentVote.getValue() == vote) {
                votes.remove(currentVote.getKey());
                break;
            }
        }

        //adds the addVote
        votes.put(contestant, vote);

        return votes;
    }

    public Map<Contestant, Integer> sumVote(Contestant contestant, int vote) {

        int voteValue = vote;
        Integer integer = votes.get(contestant);
        if(integer != null) {
            voteValue += integer;
        }

        //adds the addVote
        votes.put(contestant, voteValue);

        return votes;
    }
}
