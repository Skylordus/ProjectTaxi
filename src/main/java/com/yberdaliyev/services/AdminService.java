package com.yberdaliyev.services;

import com.yberdaliyev.models.daos.IAdminDAO;
import com.yberdaliyev.models.daos.IDriverDAO;
import com.yberdaliyev.models.pojos.Admin;
import com.yberdaliyev.models.pojos.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Yerlan on 26.02.2017.
 */
@Service
public class AdminService implements IAdminService {
    private IAdminDAO adminDAO;

    @Autowired
    public AdminService(IAdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public Admin getAdmin (Long id) {

       Admin admin = adminDAO.getById(id);

        return admin;
    }

    @Secured({"ROLE_ADMIN"})
    @Override
    public Admin generateAdmin(long id,
                               String firstname,
                               String lastname,
                               String patronymic,
                               Date birthdate,
                               String login,
                               String email) {
        Admin admin = new Admin();
        admin.setId(id);
        admin.setFirstname(firstname);
        admin.setLogin(login);
        admin.setLastname(lastname);
        admin.setPatronymic(patronymic);
        admin.setBirthdate(birthdate);
        admin.setEmail(email);
        return admin;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void updateAdmin(long id,
                               String firstname,
                               String lastname,
                               String patronymic,
                               Date birthdate,
                               String login,
                               String email) {

        Admin admin = new Admin(id,
                firstname,
                lastname,
                patronymic,
                birthdate,
                login,
                email,
                "");
        adminDAO.updateById(id,admin);
    }

    @Override
    public Long insert(Admin admin, boolean getID) {
        return adminDAO.insert(admin, getID);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void delete(Long id) {
        adminDAO.deleteById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public List<Admin> getAll() {
        return adminDAO.getAll();
    }

}
