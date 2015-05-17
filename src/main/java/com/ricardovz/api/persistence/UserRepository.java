package com.ricardovz.api.persistence;

import com.ricardovz.api.vo.UserVO;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository used to managed Users data
 * <p/>
 * Created by ricardo on 04/05/2015.
 */
public interface UserRepository extends MongoRepository<UserVO, String> {
}
