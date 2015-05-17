package com.ricardovz.api.model;

import com.ricardovz.api.dto.UserDTO;
import com.ricardovz.api.vo.UserVO;
import lombok.Data;

/**
 * Represents a User object
 * <p/>
 * Created by ricardo on 04/05/2015.
 */
@Data
public class User {

    /**
     * Identification of the user
     */
    private String id;

    /**
     * Name of the user
     */
    private String name;

    /**
     * Logically deleted
     */
    private boolean isDeleted;

    /**
     * Builds a user based on the provided parameters
     *
     * @param id User Identification
     */
    public static User createUser(String id) {

        User user = new User();
        user.setId(id);

        return user;
    }

    /**
     * Transforms DTO object into a model object
     *
     * @param userDTO DTO source object
     * @return {@link User} instance representation of the provided DTO, if the provided DTO is null it returns null
     */
    public static User fromDTO(UserDTO userDTO) {

        if (userDTO == null) {
            return null;
        }

        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());

        return user;
    }

    /**
     * Transforms VO object into a model object
     *
     * @param userVO VO source object
     * @return {@link User} instance representation of the provided DTO, if the provided VO is null it returns null
     */
    public static User fromVO(UserVO userVO) {

        if (userVO == null) {
            return null;
        }

        User user = new User();
        user.setId(userVO.getId());
        user.setName(userVO.getName());

        return user;
    }
}
