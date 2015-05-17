package com.ricardovz.api.service.impl;

import com.ricardovz.api.model.Receipt;
import com.ricardovz.api.model.User;
import com.ricardovz.api.persistence.UserRepository;
import com.ricardovz.api.service.PersistenceService;
import com.ricardovz.api.vo.ReceiptVO;
import com.ricardovz.api.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Serves the available operation over the user instances
 *
 * Created by ricardo on 04/05/2015.
 */
@Slf4j
@Service
public class UserServiceImpl implements PersistenceService<User> {

    /**
     * User repository service
     */
    private UserRepository userRepository;

    /**
     * Build a instance of the service setting the fields with the given parameters
     * @param userRepository User repository service
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {

        if (user == null) {

            return null;

        }

        UserVO userVO = new UserVO(user.getId(), user.getName(), user.isDeleted());

        return User.fromVO(userRepository.save(userVO));
    }

    @Override
    public List<User> list() {

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        List<UserVO> userVOs = userRepository.findAll(sort);

        List<User> users = new ArrayList<>(userVOs.size());

        for (UserVO userVO : userVOs) {

            users.add(User.fromVO(userVO));

        }
        return users;
    }

    @Override
    public User get(String id) {

        UserVO userVO = userRepository.findOne(id);

        return User.fromVO(userVO);
    }

    @Override
    public User delete(String id) {

        log.debug("Marking user '{}' as deleted", id);

        UserVO userVO = userRepository.findOne(id);
        if (userVO == null) {
            return null;
        }

        User user = User.fromVO(userVO);
        user.setDeleted(true);

        return save(user);
    }
}
