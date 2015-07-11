package com.ricardovz.api.eurovision.persistence;

import com.ricardovz.api.eurovision.vo.VoteVO;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository of votes
 *
 * Created by ricardo on 23/05/2015.
 */
public interface VoteRepository extends MongoRepository<VoteVO, String> {

    VoteVO findByUserId(String userId);
}
