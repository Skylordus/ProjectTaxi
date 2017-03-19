package com.yberdaliyev.services;

import com.yberdaliyev.common.exceptions.EmailExistsException;
import com.yberdaliyev.common.exceptions.LoginExistsException;
import com.yberdaliyev.models.daos.*;
import com.yberdaliyev.models.entities.ClientEntity;
import com.yberdaliyev.models.enums.USER_ROLES;
import com.yberdaliyev.models.pojos.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

import static com.yberdaliyev.models.enums.USER_ROLES.ROLE_ADMIN;
import static com.yberdaliyev.models.enums.USER_ROLES.ROLE_CLIENT;
import static com.yberdaliyev.models.enums.USER_ROLES.ROLE_DRIVER;

/**
 * Created by Yerlan on 26.02.2017.
 */
@Service
public class UserService implements IUserService {

    private PasswordEncoder encoder;

    static Logger logger = Logger.getLogger(UserService.class);

    private final IUserDAO IUserDAO;
    private final IAdminDAO IAdminDAO;
    private final IClientDAO IClientDAO;
    private final IDriverDAO IDriverDAO;

    @Autowired
    public UserService(IUserDAO IUserDAO, IAdminDAO IAdminDAO, IClientDAO IClientDAO, IDriverDAO IDriverDAO) {
        this.IUserDAO = IUserDAO;
        this.IAdminDAO = IAdminDAO;
        this.IClientDAO = IClientDAO;
        this.IDriverDAO = IDriverDAO;
    }

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public void register(USER_ROLES user_role,
                         String user_name,
                         String user_surname,
                         String user_patronymic,
                         Date user_birthdate,
                         String user_login,
                         String user_password,
                         String user_email) throws EmailExistsException, LoginExistsException {

        User user = null;

        if (user_role.equals(ROLE_CLIENT)) {
            user = new ClientEntity();
        } else if (user_role.equals(ROLE_DRIVER)) {
            user = new Driver();
        } else if (user_role.equals(ROLE_ADMIN)) {
            user = new Admin();
        }

        user.setFirstname( user_name );
        user.setLastname( user_surname );
        user.setPatronymic( user_patronymic );
        user.setBirthdate(user_birthdate);
        user.setLogin( user_login );
        user.setPwd( encoder.encode(user_password) );
        user.setEmail( user_email );

        if (user_role.equals(ROLE_CLIENT)) {
            ClientEntity client = (ClientEntity) user;
            client.setDate_registered(new Date(new java.util.Date().getTime()));
            client.setOrders_amount(0);
            IClientDAO.insert(client,false);
        } else if (user_role.equals(ROLE_DRIVER)) {
            Driver driver = (Driver) user;
            driver.setCar((long)0);
            driver.setExperience_years((long)0);
            IDriverDAO.insert(driver,false);
        } else if (user_role.equals(ROLE_ADMIN)) {
            Admin admin = (Admin) user;
            IAdminDAO.insert(admin, false);
        }

    }

    public User getUserByLoginAndRole(String login, USER_ROLES role) {

        User user = IUserDAO.getByLoginAndRole(login,role);

        return user;
    }

    public MyUserDetails getUserDetailsByLogin(String login) {
        return IUserDAO.getUserDetailsByLogin(login);
    }
}
