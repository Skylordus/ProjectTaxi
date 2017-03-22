//package com.yberdaliyev.models.daos.unused.hibernate;
//
//import com.yberdaliyev.models.daos.IAdminDAO;
//import com.yberdaliyev.models.entities.AdminEntity;
//import com.yberdaliyev.models.entities.LoginEntity;
//import com.yberdaliyev.models.enums.USER_ROLES;
//import com.yberdaliyev.models.pojos.Admin;
//import com.yberdaliyev.models.transformers.EntityToPojoTransformer;
//import com.yberdaliyev.models.transformers.PojoToEntityTransformer;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//@Transactional
//public class AdminDAO_h implements IAdminDAO {
//
//    private static Logger logger = Logger.getLogger(AdminDAO_h.class);
//
//    private EntityToPojoTransformer entityToPojo;
//    private PojoToEntityTransformer pojoToEntity;
//
//    @PersistenceContext
//    private EntityManager manager;
//
//    @Autowired
//    public void setEntityToPojo(EntityToPojoTransformer entityToPojo) {this.entityToPojo = entityToPojo;}
//    @Autowired
//    public void setPojoToEntity(PojoToEntityTransformer pojoToEntity) {this.pojoToEntity = pojoToEntity;}
//
//    @Override
//    public Long insert(Admin admin, boolean getID) {
//        logger.warn("trying to insert a new admin");
//        AdminEntity adminEntity = pojoToEntity.toAdminEntity(admin);
//        logger.warn("inserting LOGIN first");
//        LoginEntity loginEntity = new LoginEntity(admin.getLogin(),
//                                                  admin.getPwd(),
//                                                  USER_ROLES.ROLE_ADMIN,
//                                                  true);
//        manager.persist(loginEntity);
//        manager.persist(adminEntity);
//        logger.warn("inserting a new admin with ID="+adminEntity.getId());
//        if (getID) {return adminEntity.getId();}
//        else return 0L;
//    }
//
//    @Override
//    public Admin getById(long id) {
//        logger.warn("getting admin by ID="+id);
//        AdminEntity adminEntity = manager.find(AdminEntity.class, id);
//        if (adminEntity == null) return null;
//        return entityToPojo.toAdmin(adminEntity);
//    }
//
//    @Override
//    public List<Admin> getAll() {
//        logger.warn("getting all admins");
//        List<AdminEntity> adminEntities = manager.createQuery("Select a From AdminEntity a", AdminEntity.class).getResultList();
//
//        List<Admin> admins = new ArrayList<>();
//        for (AdminEntity adminEntity : adminEntities) {
//            admins.add(entityToPojo.toAdmin(adminEntity));
//        }
//        return admins;
//    }
//
//    @Override
//    public void updateById(Long id, Admin admin) {
//        logger.warn("trying to update a admin with id="+id);
//        AdminEntity adminEntity = manager.find(AdminEntity.class, id);
//        if (admin.getFirstname() != null) adminEntity.setFirstname(admin.getFirstname());
//        if (admin.getLastname() != null) adminEntity.setLastname(admin.getLastname());
//        if (admin.getPatronymic() != null) adminEntity.setPatronymic(admin.getPatronymic());
//        if (admin.getEmail() != null) adminEntity.setEmail(admin.getEmail());
//        if (admin.getBirthdate() != null) adminEntity.setBirthdate(admin.getBirthdate());
//        if (admin.getLogin() != null) adminEntity.setLogin(admin.getLogin());
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        logger.warn("deleting client with id="+id);
//        AdminEntity adminEntity = manager.find(AdminEntity.class, id);
//        manager.remove(adminEntity);
//    }
//}
