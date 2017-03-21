package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.enums.USER_ROLES;
import com.yberdaliyev.models.pojos.MyUserDetails;
import com.yberdaliyev.models.pojos.User;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IUserDAO {

    User getByLoginAndRole(String login, USER_ROLES role);

    MyUserDetails getUserDetailsByLogin(String login);

    void setFields(User user, ResultSet rs) throws SQLException;
}
