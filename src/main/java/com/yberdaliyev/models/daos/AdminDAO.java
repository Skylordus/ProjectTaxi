package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.connectors.Connector;
import com.yberdaliyev.models.pojos.Admin;
import com.yberdaliyev.models.pojos.Driver;
import org.apache.log4j.Logger;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

@Repository
public class AdminDAO implements IAdminDAO {

    private static Logger logger = Logger.getLogger(AdminDAO.class);

    private IUserDAO userDAO;
    public AdminDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private static final String SQL_INSERT_ADMIN = "INSERT INTO main.admins " +
                                                   "(firstname,lastname,patronymic,birthdate,login,email) "+
                                                   "values (?,?,?,?,?,?)";
    private static final String SQL_SELECT_ID = "SELECT * FROM main.admins " +
                                                "WHERE id=?";
    private static final String SQL_SELECT_MAXID = "SELECT max(id) FROM main.admins;";
    private static final String SQL_GET_ALL_ADMINS = "SELECT * FROM main.admins ORDER BY id ASC";
    private static final String SQL_UPDATE_ADMIN = "UPDATE main.admins SET ";
    private static final String SQL_DELETE_ADMIN = "DELETE FROM main.admins WHERE id=?;";

    @Override
    public Long insert(Admin admin, boolean getID) {
        try (PreparedStatement prepS = userDAO.insert(SQL_INSERT_ADMIN, admin) ) {
            if (prepS.executeUpdate() > 0) {
                if (!getID) return (long) -1;
                Connection conn = Connector.getConnection();
                ResultSet rs = conn.prepareStatement(SQL_SELECT_MAXID).executeQuery();
                rs.next();
                Long id = rs.getLong("max");
                return id;
            }
        } catch (PSQLException e) {
            logger.trace(e.getCause());
            logger.error("PSQL exc. in insert admin method",e);
        } catch (SQLException e) {
            logger.error("SQL exc. in insert admin method",e);
        }
        return null;
    }

    @Override
    public Admin getById(long id) {
        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_SELECT_ID)) {
            prepS.setLong(1,id);
            ResultSet resultSet = prepS.executeQuery();
            Admin admin = new Admin();
            resultSet.next();
            userDAO.setFields(admin,resultSet);
            return admin;
        } catch (SQLException e ) {
            logger.error("error",e);
        }
        return null;
    }

    @Override
    public ArrayList<Admin> getAll() {
        Admin admin;
        ArrayList<Admin> admins = new ArrayList<>();
        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_GET_ALL_ADMINS) ) {
            ResultSet resultSet = prepS.executeQuery();
            while (resultSet.next()){
                admin = new Admin();
                admin.setId(resultSet.getLong("id"));
                admin.setFirstname(resultSet.getString("firstname"));
                admin.setLastname(resultSet.getString("lastname"));
                admin.setPatronymic(resultSet.getString("patronymic"));
                admin.setBirthdate(resultSet.getDate("birthdate"));
                admin.setLogin(resultSet.getString("login"));
                admin.setEmail(resultSet.getString("email"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            logger.error("sql error in get all admins",e);
        }
        return admins;
    }

    @Override
    public boolean updateById(Long id, Properties columns) {
        if (columns.isEmpty()) {
            logger.warn("empty update admin request");
            return true;
        }
        String query = SQL_UPDATE_ADMIN;
        for (String key : columns.stringPropertyNames()) {
            query += key+"="+"?, ";
        }
        query = query.substring(0,query.length()-2);
        query += " WHERE id="+id+";";
        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(query)) {
            int i = 1;
            for (String key : columns.stringPropertyNames()) {
                switch (key) {
                    default:
                        prepS.setString(i,columns.getProperty(key));
                        i++;
                        break;
                    case "birthdate":
                        prepS.setDate(i, Date.valueOf(columns.getProperty(key)));
                        i++;
                }
            }
            logger.warn(prepS);
            if (prepS.executeUpdate()>0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("sql error in adminDAO.updatebyId() method",e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_DELETE_ADMIN)) {
            prepS.setLong(1,id);
            if (prepS.executeUpdate()>0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("sql error in adminDAO.deleteById() method",e);
        }
        return false;
    }
}
