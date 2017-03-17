package com.yberdaliyev.services;

import com.yberdaliyev.models.pojos.Admin;
import com.yberdaliyev.models.pojos.Driver;

import java.util.ArrayList;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IAdminService {
    Admin getAdmin(Long id);
    Admin generateAdmin(String id,
                          String firstname,
                          String lastname,
                          String patronymic,
                          String birthdate,
                          String login,
                          String email);

    boolean updateAdmin(String id,
                         String firstname,
                         String lastname,
                         String patronymic,
                         String birthdate,
                         String login,
                         String email);

    Long insert(Admin admin, boolean getID);
    boolean delete(Long id);
    ArrayList<Admin> getAll();
}
