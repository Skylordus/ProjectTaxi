//package com.yberdaliyev.models.daos.unused.hibernate;
//
//import com.yberdaliyev.models.daos.IUserDAO;
//import com.yberdaliyev.models.entities.AdminEntity;
//import com.yberdaliyev.models.entities.ClientEntity;
//import com.yberdaliyev.models.entities.DriverEntity;
//import com.yberdaliyev.models.entities.LoginEntity;
//import com.yberdaliyev.models.enums.USER_ROLES;
//import com.yberdaliyev.models.pojos.MyUserDetails;
//import com.yberdaliyev.models.pojos.User;
//import com.yberdaliyev.models.transformers.EntityToPojoTransformer;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//@Transactional
//@Repository
//public class UserDAO_h implements IUserDAO {
//    private static Logger logger = Logger.getLogger(UserDAO_h.class);
//    private EntityToPojoTransformer entityToPojo;
//
//    @PersistenceContext
//    private EntityManager manager;
//
//    @Autowired
//    public void setEntityToPojo(EntityToPojoTransformer entityToPojo) {this.entityToPojo = entityToPojo;}
//
//    @Override
//    public User getByLoginAndRole(String login, USER_ROLES role) {
//
//        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
//        CriteriaQuery criteriaQuery = null;
//        Root root = null;
//
//        switch (role) {
//            case ROLE_ADMIN:
//                criteriaQuery = criteriaBuilder.createQuery(AdminEntity.class);
//                root = criteriaQuery.from(AdminEntity.class);
//                break;
//            case ROLE_CLIENT:
//                criteriaQuery = criteriaBuilder.createQuery(ClientEntity.class);
//                root = criteriaQuery.from(ClientEntity.class);
//                break;
//            case ROLE_DRIVER:
//                criteriaQuery = criteriaBuilder.createQuery(DriverEntity.class);
//                root = criteriaQuery.from(DriverEntity.class);
//        }
//
//        criteriaQuery.select(root);
//        criteriaQuery.where(
//                criteriaBuilder.and(
//                        criteriaBuilder.equal(root.get("login"), login)
//                )
//        );
//
//        User user = (User) manager.createQuery(criteriaQuery).getSingleResult();
//
//        return user;
//
//    }
//
//    @Override
//    public void setFields(User user, ResultSet rs) throws SQLException {
//        user.setBirthdate(rs.getDate("birthdate"));
//        user.setLastname(rs.getString("lastname"));
//        user.setFirstname(rs.getString("firstname"));
//        user.setEmail(rs.getString("email"));
//        user.setLogin(rs.getString("login"));
//        user.setPatronymic(rs.getString("patronymic"));
//        user.setId(rs.getLong("id"));
//    }
//
//    @Override
//    public MyUserDetails getUserDetailsByLogin(String login) {
//        LoginEntity loginEntity = manager.find(LoginEntity.class,login);
//        return entityToPojo.toUserDetails(loginEntity);
//    }
//}
