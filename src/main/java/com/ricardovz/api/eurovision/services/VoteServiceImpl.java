package com.ricardovz.api.eurovision.services;

import com.ricardovz.api.eurovision.model.Contestant;
import com.ricardovz.api.eurovision.model.Vote;
import com.ricardovz.api.eurovision.persistence.VoteRepository;
import com.ricardovz.api.eurovision.vo.VoteVO;
import com.ricardovz.api.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Votes services
 * Created by ricardo on 23/05/2015.
 */
@Service
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;

    /**
     * Default constructor
     * @param voteRepository {@link VoteRepository} instance used to persist the data
     */
    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public Vote save(Vote vote) {

        if (voteRepository.findByUserId(vote.getUser().getId()) != null) {
            throw  new IllegalArgumentException("A user can votes only once");
        }

        Map<String, Integer> votes = new HashMap<>(vote.getVotes().size());
        for(Map.Entry<Contestant, Integer> voteElement :  vote.getVotes().entrySet()) {
            votes.put(voteElement.getKey().getId(), voteElement.getValue());
        }


        String id = new ObjectId().toHexString();
        VoteVO voteVO = new VoteVO(id, vote.getUser().getId(), votes);

        voteRepository.save(voteVO);

        return vote;
    }

    @Override
    public List<Vote> list() {

        voteRepository.findAll();
        return null;
    }

    @Override
    public List<Contestant> getContestantList() {

        List<VoteVO> votes = voteRepository.findAll();

        User user = new User();
        user.setName("summary");
        Vote summary = new Vote(user, null);

        for (VoteVO vote : votes) {
            for (Map.Entry<String, Integer> voteEntry : vote.getVotes().entrySet()) {
                Contestant contestant = new Contestant(voteEntry.getKey(), voteEntry.getKey());
                summary.sumVote(contestant, voteEntry.getValue());
            }
        }

        List<Contestant> contestants = new ArrayList<>(summary.getVotes().size());

        for (Map.Entry<Contestant, Integer> voteEntry : summary.getVotes().entrySet()) {

            Contestant contestant = voteEntry.getKey();
            contestant.setPoints(voteEntry.getValue());

            contestants.add(contestant);
        }
        return contestants;
    }

    @Override
    public Vote get(String id) {
        return null;
    }

    @Override
    public Vote delete(String id) {
        return null;
    }
}
