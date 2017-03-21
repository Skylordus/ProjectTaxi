package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.pojos.Admin;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IAdminDAO {

    Long insert(Admin admin, boolean getID);
    List<Admin> getAll();
    void updateById(Long id, Admin admin);
    void deleteById(Long id);
    Admin getById(long id);
}
