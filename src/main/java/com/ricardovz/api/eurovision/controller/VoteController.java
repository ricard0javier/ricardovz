package com.ricardovz.api.eurovision.controller;

import com.ricardovz.api.eurovision.model.Contestant;
import com.ricardovz.api.eurovision.model.Vote;
import com.ricardovz.api.eurovision.services.VoteService;
import com.ricardovz.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller to be used for voting iun the eruovision event
 *
 * Created by ricardo on 23/05/2015.
 */
@RestController("/eurovision")
public class VoteController {

    private VoteService voteService;

    @Autowired
    public VoteController(VoteService votePersistenceService) {
        this.voteService = votePersistenceService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Vote vote(@RequestParam String userID, @RequestParam String[] contestantVoted) {

        User user = new User();
        user.setId(userID);

        Vote vote = new Vote(user, null);

        int voteValue = 1;
        for(String contestantId : contestantVoted) {

            Contestant contestant = new Contestant(contestantId, null);
            vote.addVote(contestant, voteValue++);

            // voteValue is between 1 and 12 but not 9 or 11
            if (voteValue == 9 || voteValue == 11) {
                voteValue++;
            }

            if(voteValue > 12) {
                break;
            }

        }


        return voteService.save(vote);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Contestant> getContestants() {

        return voteService.getContestantList();
    }
}
