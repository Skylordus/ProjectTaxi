//package com.yberdaliyev.models.daos.unused;
//
//import com.yberdaliyev.models.connectors.Connector;
//import com.yberdaliyev.models.daos.IUserDAO;
//import com.yberdaliyev.models.enums.USER_ROLES;
//import com.yberdaliyev.models.pojos.*;
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Repository;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//@Repository
//public class UserDAOold implements IUserDAO {
//    private static Logger logger = Logger.getLogger(UserDAOold.class);
//    private static final String SQL_INSERT_LOGIN = "INSERT INTO main.logins " +
//            "(login,pwd,role) "+
//            "values (?,?,?::main.user_role);";
//    private static final String SQL_SELECT_LOGIN = "SELECT * FROM main.logins " +
//                                                   "WHERE login=? AND pwd=?";
//    private static final String SQL_SELECT_LOGIN_ONLY = "SELECT * FROM main.logins " +
//                                                          "WHERE login=?";
//
//    @Override
//    public PreparedStatement insert(String statement, User user) {
//        try {Connection conn = Connector.getConnection();
//            PreparedStatement prepS = conn.prepareStatement(SQL_INSERT_LOGIN+statement);
//            prepS.setString(1,user.getLogin());
//            prepS.setString(2,user.getPwd());
//
//            USER_ROLES role = USER_ROLES.ROLE_ADMIN;
//            if (user instanceof Client) {role=USER_ROLES.ROLE_CLIENT;} else
//            if (user instanceof Driver) {role=USER_ROLES.ROLE_DRIVER;}
//            prepS.setString(3, role.toString());
//
//            prepS.setString(4,user.getFirstname());
//            prepS.setString(5,user.getLastname());
//            prepS.setString(6,user.getPatronymic());
//            prepS.setDate(7,user.getBirthdate());
//            prepS.setString(8,user.getLogin());
//            prepS.setString(9,user.getEmail());
//            return prepS;
//        } catch (SQLException e) {
//            logger.error("sql error in insertUser method",e);
//        }
//        return null;
//    }
//
//    @Override
//    public User getByLoginAndRole(String login, USER_ROLES role) {
//        try (Connection conn = Connector.getConnection()){
//
//            String query = "SELECT * FROM ";
//
//            switch (role) {
//                case ROLE_ADMIN:
//                    query += "main.admins ";
//                    break;
//                case ROLE_CLIENT:
//                    query += "main.clients ";
//                    break;
//                case ROLE_DRIVER:
//                    query += "main.drivers ";
//            }
//            query += "WHERE login=?";
//            PreparedStatement prep = conn.prepareStatement(query);
//            prep.setString(1,login);
//            ResultSet rs = prep.executeQuery();
//            if (!rs.next()) return null;
//            switch (role) {
//                case ROLE_ADMIN:
//                    Admin admin = new Admin();
//                    setFields(admin,rs);
//                    return admin;
//                case ROLE_CLIENT:
//                    Client client = new Client();
//                    setFields(client,rs);
//                    client.setDate_registered(rs.getDate("date_registered"));
//                    client.setOrders_amount(rs.getLong("orders_amount"));
//                    client.setOrder(rs.getLong("order"));
//                    return client;
//                case ROLE_DRIVER:
//                    Driver driver = new Driver();
//                    setFields(driver,rs);
//                    driver.setCar(rs.getLong("car"));
//                    driver.setExperience_years(rs.getLong("experience_years"));
//                    driver.setOrder(rs.getLong("order"));
//                    return driver;
//            }
//        } catch (SQLException e) {
//            logger.error("SQL exception: getBY login and role UserDAO",e);
//        }
//        return null;
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
//        MyUserDetails myUserDetails = new MyUserDetails();
//        try (Connection conn = Connector.getConnection();
//             PreparedStatement prepS = conn.prepareStatement(SQL_SELECT_LOGIN_ONLY)) {
//            prepS.setString(1,login);
//            ResultSet rs = prepS.executeQuery();
//            if ( !rs.next() ) return null;
//            myUserDetails.setLogin(login);
//            myUserDetails.setPwd(rs.getString("pwd"));
//            myUserDetails.setRole(rs.getString("role"));
//            myUserDetails.setEnabled(rs.getBoolean("enabled"));
//        } catch (SQLException e) {
//            logger.error("SQL exception: get MyUserDetails BY login UserDAO",e);
//        }
//        return myUserDetails;
//    }
//}
