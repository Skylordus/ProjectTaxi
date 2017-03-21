//package com.yberdaliyev.models.daos;
//
//import com.yberdaliyev.models.connectors.Connector;
//import com.yberdaliyev.models.pojos.Car;
//import org.apache.log4j.Logger;
//import org.postgresql.util.PSQLException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Properties;
//
//@Repository
//public class CarDAO_old implements ICarDAO {
//    private IUserDAO userDAO;
//
//    @Autowired
//    public CarDAO_old(IUserDAO userDAO) {
//        this.userDAO = userDAO;
//    }
//
//    private static Logger logger = Logger.getLogger(CarDAO_old.class);
//
//
//    private static final String SQL_SELECT_ID = "SELECT * FROM main.cars " +
//            "WHERE id=?";
//    private static final String SQL_INSERT_CAR = "INSERT INTO main.cars " +
//            "(manufacturer,model,regnum,color) "+
//            "values (?,?,?,?)";
//    private static final String SQL_SELECT_MAXID = "SELECT max(id) FROM main.cars;";
//    private static final String SQL_GET_ALL_CARS = "SELECT * FROM main.cars ORDER BY id ASC";
//    private static final String SQL_UPDATE_CAR = "UPDATE main.cars SET ";
//    private static final String SQL_DELETE_CAR = "DELETE FROM main.cars WHERE id=?;";
//
//    @Override
//    public Long insert(Car car, boolean getID) {
//        try ( Connection conn = Connector.getConnection();
//              PreparedStatement prepS = conn.prepareStatement(SQL_INSERT_CAR) ) {
//            prepS.setString(1,car.getManufacturer());
//            prepS.setString(2,car.getModel());
//            prepS.setString(3,car.getRegnum());
//            prepS.setString(4,car.getColor());
//            if (prepS.executeUpdate() > 0) {
//                if (!getID) return (long) -1;
//                ResultSet rs = conn.prepareStatement(SQL_SELECT_MAXID).executeQuery();
//                rs.next();
//                Long id = rs.getLong("max");
//                return id;
//            }
//        } catch (PSQLException e) {
//            logger.trace(e.getCause());
//            logger.error("PSQL exc. in insert car method",e);
//        } catch (SQLException e) {
//            logger.error("SQL exc. in insert car method",e);
//        }
//        return null;
//    }
//
//    @Override
//    public Car getById(long id) {
//        try (Connection conn = Connector.getConnection();
//             PreparedStatement prepS = conn.prepareStatement(SQL_SELECT_ID)) {
//            prepS.setLong(1,id);
//            ResultSet resultSet = prepS.executeQuery();
//            if (!resultSet.next()) return null;
//
//            Car car = new Car();
//            car.setColor(resultSet.getString("color"));
//            car.setId(resultSet.getLong("id"));
//            car.setManufacturer(resultSet.getString("manufacturer"));
//            car.setModel(resultSet.getString("model"));
//            car.setRegnum(resultSet.getString("regnum"));
//            return car;
//        } catch (SQLException e ) {
//            logger.error("error",e);
//        }
//        return null;
//    }
//
//    @Override
//    public ArrayList<Car> getAll() {
//        Car car;
//        ArrayList<Car> cars = new ArrayList<>();
//        try (Connection conn = Connector.getConnection();
//             PreparedStatement prepS = conn.prepareStatement(SQL_GET_ALL_CARS) ) {
//            ResultSet resultSet = prepS.executeQuery();
//            while (resultSet.next()){
//                car = new Car();
//                car.setId(resultSet.getLong("id"));
//                car.setManufacturer(resultSet.getString("manufacturer"));
//                car.setModel(resultSet.getString("model"));
//                car.setRegnum(resultSet.getString("regnum"));
//                car.setColor(resultSet.getString("color"));
//                cars.add(car);
//            }
//        } catch (SQLException e) {
//            logger.error("sql error in get all cars",e);
//        }
//        return cars;
//    }
//
//    @Override
//    public boolean updateById(Long id, Properties columns) {
//        if (columns.isEmpty()) {
//            logger.warn("empty update car request");
//            return true;
//        }
//        String query = SQL_UPDATE_CAR;
//        for (String key : columns.stringPropertyNames()) {
//            query += key+"="+"?, ";
//        }
//        query = query.substring(0,query.length()-2);
//        query += " WHERE id="+id+";";
//        try (Connection conn = Connector.getConnection();
//             PreparedStatement prepS = conn.prepareStatement(query)) {
//            int i = 1;
//            for (String key : columns.stringPropertyNames()) {
//                prepS.setString(i,columns.getProperty(key));
//                i++;
//            }
//            logger.warn(prepS);
//            if (prepS.executeUpdate()>0) {
//                return true;
//            }
//        } catch (SQLException e) {
//            logger.error("sql error in carDAO.updatebyId() method",e);
//        }
//        return false;
//    }
//
//    @Override
//    public boolean deleteById(Long id) {
//        try (Connection conn = Connector.getConnection();
//             PreparedStatement prepS = conn.prepareStatement(SQL_DELETE_CAR)) {
//            prepS.setLong(1,id);
//            if (prepS.executeUpdate()>0) {
//                return true;
//            }
//        } catch (SQLException e) {
//            logger.error("sql error in carDAO.deleteById() method",e);
//        }
//        return false;
//    }
//
//}
