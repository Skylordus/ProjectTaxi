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
    public Admin generateAdmin(String id,
                                 String firstname,
                                 String lastname,
                                 String patronymic,
                                 String birthdate,
                                 String login,
                                 String email) {
        Admin admin = new Admin();
        admin.setId(Long.parseLong(id));
        admin.setFirstname(firstname);
        admin.setLogin(login);
        admin.setLastname(lastname);
        admin.setPatronymic(patronymic);
        admin.setBirthdate(Date.valueOf(birthdate));
        admin.setEmail(email);
        return admin;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public boolean updateAdmin(String id,
                                String firstname,
                                String lastname,
                                String patronymic,
                                String birthdate,
                                String login,
                                String email) {
        long admin_id = Long.parseLong(id);
        Properties columns = new Properties();
        if (!firstname.isEmpty()) columns.put("firstname",firstname);
        if (!lastname.isEmpty()) columns.put("lastname",lastname);
        if (!patronymic.isEmpty()) columns.put("patronymic",patronymic);
        if (!birthdate.isEmpty()) columns.put("birthdate",birthdate);
        if (!login.isEmpty()) columns.put("login",login);
        if (!email.isEmpty()) columns.put("email",email);
        return adminDAO.updateById(admin_id,columns);
    }

    @Override
    public Long insert(Admin admin, boolean getID) {
        return adminDAO.insert(admin, getID);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public boolean delete(Long id) {
        if (adminDAO.deleteById(id)) {
            return true;
        }
        return false;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public ArrayList<Admin> getAll() {
        return adminDAO.getAll();
    }

}
