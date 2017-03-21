package com.yberdaliyev.services;

import com.yberdaliyev.models.pojos.Admin;
import com.yberdaliyev.models.pojos.Driver;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IAdminService {
    Admin getAdmin(Long id);
    Admin generateAdmin(long id,
                        String firstname,
                        String lastname,
                        String patronymic,
                        Date birthdate,
                        String login,
                        String email);

    void updateAdmin(long id,
                         String firstname,
                         String lastname,
                         String patronymic,
                         Date birthdate,
                         String login,
                         String email);

    Long insert(Admin admin, boolean getID);
    void delete(Long id);
    List<Admin> getAll();
}
