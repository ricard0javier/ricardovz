package com.ricardovz.api.controller;

import com.ricardovz.api.dto.UserDTO;
import com.ricardovz.api.model.User;
import com.ricardovz.api.service.PersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * User Controller service
 * Created by ricardo on 04/05/2015.
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Receipt service
     */
    private PersistenceService<User> userService;

    /**
     * Builds a {@link UserController} instance based on the provided services
     *
     * @param userService user service to be used
     */
    @Autowired
    public UserController(PersistenceService<User> userService) {
        this.userService = userService;
    }

    /**
     * Saves user details
     *
     * @param userDTO user data transfer object
     * @return result message
     */
    @RequestMapping(method = RequestMethod.POST)
    public UserDTO save(@RequestBody UserDTO userDTO) {

        log.debug("Attempt to save user data '{}'", userDTO.toString());

        return getUserDTO(userService.save(User.fromDTO(userDTO)));
    }

    /**
     * Returns a List of the current users on the system
     *
     * @return {@link List} of {@link UserDTO} instances
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<UserDTO> list() {

        log.debug("Attempt to get list of users");

        List<User> users = userService.list();
        List<UserDTO> userDTOs = new ArrayList<>(users.size());

        for (User user : users) {
            userDTOs.add(getUserDTO(user));
        }

        return userDTOs;
    }

    /**
     * Convert a model user into a user DTO
     * @param user model instance
     * @return DTO instance
     */
    private UserDTO getUserDTO(User user) {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());

        return userDTO;
    }
}
