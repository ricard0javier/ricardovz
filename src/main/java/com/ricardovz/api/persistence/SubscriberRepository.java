package com.ricardovz.api.persistence;

import com.ricardovz.api.vo.SubscriberVO;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository used to managed Subscribers data
 * <p/>
 * Created by ricardo on 06/07/2015.
 */
public interface SubscriberRepository extends MongoRepository<SubscriberVO, String> {

    /**
     * find a subscriber by email
     */
    SubscriberVO findByEmail(String email);
}
