package com.yberdaliyev.services;

import com.yberdaliyev.entities.AdminEntity;
import com.yberdaliyev.models.pojos.Admin;
import com.yberdaliyev.repositories.AdminRepository;
import com.yberdaliyev.transformers.EntityToPojoTransformer;
import com.yberdaliyev.transformers.PojoToEntityTransformer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yerlan on 26.02.2017.
 */
@Service
public class AdminService implements IAdminService {
    private static Logger logger = Logger.getLogger(AdminService.class);
    private AdminRepository repository;
    private EntityToPojoTransformer entityToPojo;
    private PojoToEntityTransformer pojoToEntity;

    @Autowired
    public void setEntityToPojo(EntityToPojoTransformer entityToPojo) {this.entityToPojo = entityToPojo;}
    @Autowired
    public void setPojoToEntity(PojoToEntityTransformer pojoToEntity) {this.pojoToEntity = pojoToEntity;}

    @Autowired
    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }

    @Secured({"ROLE_ADMIN"})
    public Admin getAdmin (Long id) {
       Admin admin = entityToPojo.toAdmin(repository.findOne(id));
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
        repository.save(pojoToEntity.toAdminEntity(admin));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public Long insert(Admin admin) {
        return repository.save(pojoToEntity.toAdminEntity(admin)).getId();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public List<Admin> getAll() {
        logger.warn("getting all admins");
        List<AdminEntity> adminEntities = repository.findAll();
        List<Admin> admins = new ArrayList<>();
        for (AdminEntity adminEntity : adminEntities) {
            admins.add(entityToPojo.toAdmin(adminEntity));
        }
        return admins;
    }

}
