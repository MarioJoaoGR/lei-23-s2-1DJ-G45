package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.RequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.mappers.RequestMapper;

import java.io.Serializable;
import java.util.*;

/**
 * The type Listing repository.
 */
public class UserLoginRepository implements Serializable {


    private final List<UserLogin> userList = new ArrayList<>();

    /**
     * Gets user list.
     *
     * @return the user list
     */
    public List<UserLogin> getUserList() {
        return userList;
    }

    /**
     * Add user.
     *
     * @param userLogin the user login
     */
    public void add(UserLogin userLogin) {
        userList.add(userLogin);
    }


}

