package com.yberdaliyev.services;

import com.yberdaliyev.models.enums.USER_ROLES;
import com.yberdaliyev.models.pojos.MyUserDetails;
import com.yberdaliyev.models.pojos.User;

import java.util.Date;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IUserService {
    void register(USER_ROLES user_role,
                  String user_name,
                  String user_surname,
                  String user_patronymic,
                  Date user_birthdate,
                  String user_login,
                  String user_password,
                  String user_email);
    User getUserByLoginAndRole(String login, USER_ROLES role);
    MyUserDetails getUserDetailsByLogin(String login);
    boolean validateSpecialPassword(String pwd, USER_ROLES role);

}
