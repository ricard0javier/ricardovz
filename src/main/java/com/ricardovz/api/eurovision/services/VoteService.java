package com.ricardovz.api.eurovision.services;

import com.ricardovz.api.eurovision.model.Contestant;
import com.ricardovz.api.eurovision.model.Vote;
import com.ricardovz.api.service.PersistenceService;

import java.util.List;

/**
 * Vote service interface
 * Created by ricardo on 23/05/2015.
 */
public interface VoteService extends PersistenceService<Vote> {

    List<Contestant> getContestantList();
}
