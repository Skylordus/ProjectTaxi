//package com.yberdaliyev.models.daos.unused;
//
//import com.yberdaliyev.models.connectors.Connector;
//import com.yberdaliyev.models.daos.IDriverDAO;
//import com.yberdaliyev.models.daos.IUserDAO;
//import com.yberdaliyev.models.pojos.Driver;
//import org.apache.log4j.Logger;
//import org.postgresql.util.PSQLException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Properties;
//
//@Repository
//public class DriverDAO_old implements IDriverDAO {
//    private IUserDAO userDAO;
//
//    @Autowired
//    public DriverDAO_old(IUserDAO userDAO) {
//        this.userDAO = userDAO;
//    }
//
//    private static Logger logger = Logger.getLogger(DriverDAO_old.class);
//
//    private static final String SQL_INSERT_DRIVER = "INSERT INTO main.drivers " +
//            "(firstname,lastname,patronymic,birthdate,login,email,experience_years,car,\"order\") "+
//            "values (?,?,?,?,?,?,?,?,?)";
//    private static final String SQL_SELECT_ID = "SELECT * FROM main.drivers " +
//            "WHERE id=?";
//    private static final String SQL_SELECT_MAXID = "SELECT max(id) FROM main.drivers;";
//    private static final String SQL_GET_ALL_DRIVERS = "SELECT * FROM main.drivers ORDER BY id ASC";
//    private static final String SQL_UPDATE_DRIVER = "UPDATE main.drivers SET ";
//    private static final String SQL_DELETE_DRIVER = "DELETE FROM main.drivers WHERE id=?;";
//    private static final String SQL_UPDATE_ORDER = "UPDATE main.drivers " +
//            "SET \"order\"=? WHERE id=?;";
//
//    @Override
//    public Long insert(Driver driver, boolean getID) {
//        try (PreparedStatement prepS = userDAO.insert(SQL_INSERT_DRIVER, driver) ) {
//            if (driver.getExperience_years()==null) {prepS.setNull(10,Types.INTEGER);}
//            else {prepS.setLong(10, driver.getExperience_years());}
//            if (driver.getCar()==null) {prepS.setNull(11,Types.INTEGER);}
//            else {prepS.setLong(11, driver.getCar());}
//            if (driver.getOrder()==null) {prepS.setNull(12,Types.INTEGER);}
//            else {prepS.setLong(12,driver.getOrder());}
//
//            if (prepS.executeUpdate() > 0) {
//                if (!getID) return (long) -1;
//                Connection conn = Connector.getConnection();
//                ResultSet rs = conn.prepareStatement(SQL_SELECT_MAXID).executeQuery();
//                rs.next();
//                Long id = rs.getLong("max");
//                return id;
//            }
//        } catch (PSQLException e) {
//            logger.warn(e.getCause());
//            logger.error("PSQL exc. in insert driver method",e);
//        } catch (SQLException e) {
//            logger.error("SQL exc. in insert driver method",e);
//        }
//        return null;
//    }
//
//    @Override
//    public Driver getById(long id) {
//        try (Connection conn = Connector.getConnection();
//             PreparedStatement prepS = conn.prepareStatement(SQL_SELECT_ID)) {
//            prepS.setLong(1,id);
//            ResultSet resultSet = prepS.executeQuery();
//            Driver driver = new Driver();
//            resultSet.next();
//            userDAO.setFields(driver,resultSet);
//            driver.setExperience_years(resultSet.getLong("experience_years"));
//            driver.setCar(resultSet.getLong("car"));
//            return driver;
//        } catch (SQLException e ) {
//            logger.error("error",e);
//        }
//        return null;
//    }
//
//    @Override
//    public ArrayList<Driver> getAll() {
//        Driver driver;
//        ArrayList<Driver> drivers = new ArrayList<>();
//        try (Connection conn = Connector.getConnection();
//             PreparedStatement prepS = conn.prepareStatement(SQL_GET_ALL_DRIVERS) ) {
//            ResultSet resultSet = prepS.executeQuery();
//            while (resultSet.next()){
//                driver = new Driver();
//                driver.setId(resultSet.getLong("id"));
//                driver.setFirstname(resultSet.getString("firstname"));
//                driver.setLastname(resultSet.getString("lastname"));
//                driver.setPatronymic(resultSet.getString("patronymic"));
//                driver.setExperience_years(resultSet.getLong("experience_years"));
//                driver.setCar(resultSet.getLong("car"));
//                driver.setBirthdate(resultSet.getDate("birthdate"));
//                driver.setLogin(resultSet.getString("login"));
//                driver.setEmail(resultSet.getString("email"));
//                driver.setOrder(resultSet.getLong("order"));
//                drivers.add(driver);
//            }
//        } catch (SQLException e) {
//            logger.error("sql error in get all clients",e);
//        }
//        return drivers;
//    }
//
//    @Override
//    public boolean updateById(Long id, Properties columns) {
//        if (columns.isEmpty()) {
//            logger.warn("empty update driver request");
//            return true;
//        }
//        String query = SQL_UPDATE_DRIVER;
//        for (String key : columns.stringPropertyNames()) {
//            query += key+"="+"?, ";
//        }
//        query = query.substring(0,query.length()-2);
//        query += " WHERE id="+id+";";
//        try (Connection conn = Connector.getConnection();
//             PreparedStatement prepS = conn.prepareStatement(query)) {
//            int i = 1;
//            for (String key : columns.stringPropertyNames()) {
//                switch (key) {
//                    default:
//                        prepS.setString(i,columns.getProperty(key));
//                        i++;
//                        break;
//                    case "car":
//                    case "\"order\"":
//                    case "experience_years":
//                        prepS.setInt(i,Integer.parseInt(columns.getProperty(key)));
//                        i++;
//                        break;
//                    case "birthdate":
//                        prepS.setDate(i, Date.valueOf(columns.getProperty(key)));
//                        i++;
//                }
//            }
//            logger.warn(prepS);
//            if (prepS.executeUpdate()>0) {
//                return true;
//            }
//        } catch (SQLException e) {
//            logger.error("sql error in driverDAO.updatebyId() method",e);
//        }
//        return false;
//    }
//
//    @Override
//    public boolean deleteById(Long id) {
//        try (Connection conn = Connector.getConnection();
//             PreparedStatement prepS = conn.prepareStatement(SQL_DELETE_DRIVER)) {
//            prepS.setLong(1,id);
//            if (prepS.executeUpdate()>0) {
//                return true;
//            }
//        } catch (SQLException e) {
//            logger.error("sql error in driverDAO.deleteById() method",e);
//        }
//        return false;
//    }
//
//    @Override
//    public boolean updateOrder(Long driver_id, Long order_id) {
//        try (Connection conn = Connector.getConnection();
//             PreparedStatement prepS = conn.prepareStatement(SQL_UPDATE_ORDER) ){
//            prepS.setLong(1, order_id);
//            prepS.setLong(2, driver_id);
//            if (prepS.executeUpdate() > 0) return true;
//        } catch (SQLException e) {
//            logger.error("sql error in updateOrder method in driverDAO",e);
//        }
//        return false;
//    }
//}
