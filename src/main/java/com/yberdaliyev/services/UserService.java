package com.yberdaliyev.services;

import com.yberdaliyev.common.exceptions.EmailExistsException;
import com.yberdaliyev.common.exceptions.LoginExistsException;
import com.yberdaliyev.models.daos.*;
import com.yberdaliyev.models.entities.ClientEntity;
import com.yberdaliyev.models.entities.LoginEntity;
import com.yberdaliyev.models.enums.USER_ROLES;
import com.yberdaliyev.models.pojos.*;
import com.yberdaliyev.models.repositories.*;
import com.yberdaliyev.models.transformers.EntityToPojoTransformer;
import com.yberdaliyev.models.transformers.PojoToEntityTransformer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.yberdaliyev.models.enums.USER_ROLES.ROLE_ADMIN;
import static com.yberdaliyev.models.enums.USER_ROLES.ROLE_CLIENT;
import static com.yberdaliyev.models.enums.USER_ROLES.ROLE_DRIVER;

/**
 * Created by Yerlan on 26.02.2017.
 */
@Service
public class UserService implements IUserService {

    private PasswordEncoder encoder;
    private DriverRepository driverRepository;
    private ClientRepository clientRepository;
    private AdminRepository adminRepository;
    private LoginRepository loginRepository;
    private EntityToPojoTransformer entityToPojo;
    private PojoToEntityTransformer pojoToEntity;
    static Logger logger = Logger.getLogger(UserService.class);
    @Autowired
    public void setEntityToPojo(EntityToPojoTransformer entityToPojo) {this.entityToPojo = entityToPojo;}
    @Autowired
    public void setPojoToEntity(PojoToEntityTransformer pojoToEntity) {this.pojoToEntity = pojoToEntity;}
    @Autowired
    public void setDriverRepository(DriverRepository driverRepository) {this.driverRepository = driverRepository;}
    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {this.clientRepository = clientRepository;}
    @Autowired
    public void setAdminRepository(AdminRepository adminRepository) {this.adminRepository = adminRepository;}
    @Autowired
    public void setLoginRepository(LoginRepository loginRepository) {this.loginRepository = loginRepository;}
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
            user = new Client();
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
            Client client = (Client) user;
            client.setDate_registered(new Date(new java.util.Date().getTime()));
            clientRepository.save(pojoToEntity.toClientEntity(client));
        } else if (user_role.equals(ROLE_DRIVER)) {
            Driver driver = (Driver) user;
            driverRepository.save(pojoToEntity.toDriverEntity(driver));
        } else if (user_role.equals(ROLE_ADMIN)) {
            Admin admin = (Admin) user;
            adminRepository.save(pojoToEntity.toAdminEntity(admin));
        }

    }

    public User getUserByLoginAndRole(String login, USER_ROLES role) {
        User user = null;
        if (role.equals(ROLE_CLIENT)) {
            user = entityToPojo.toClient(clientRepository.findByLogin(login));
        } else if (role.equals(ROLE_DRIVER)) {
            user = entityToPojo.toDriver(driverRepository.findByLogin(login));
        } else if (role.equals(ROLE_ADMIN)) {
            user = entityToPojo.toAdmin(adminRepository.findByLogin(login));
        }
        return user;
    }

    public MyUserDetails getUserDetailsByLogin(String login) {
        LoginEntity loginEntity = loginRepository.findOne(login);
        MyUserDetails userDetails = new MyUserDetails(loginEntity.getLogin(),
                                                      loginEntity.getPwd(),
                                                      loginEntity.getRole(),
                                                      loginEntity.getEmail(),
                                                      loginEntity.isEnabled());
        return userDetails;
    }

    public boolean validateSpecialPassword(String pwd, USER_ROLES role) {
        LoginEntity reg;
        switch (role) {
            case ROLE_CLIENT:
                return true;
            case ROLE_ADMIN:
                reg = loginRepository.findOne("ADMIN_REGISTRATION");
                if (encoder.matches(pwd,reg.getPwd())) return true;
                break;
            case ROLE_DRIVER:
                reg = loginRepository.findOne("DRIVER_REGISTRATION");
                if (encoder.matches(pwd,reg.getPwd())) return true;
                break;
        }
        return false;
    }
}
