//package com.yberdaliyev.models.daos.unused;
//
//import com.yberdaliyev.models.connectors.Connector;
//import com.yberdaliyev.models.daos.IOrderDAO;
//import com.yberdaliyev.models.pojos.Order;
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Repository;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Properties;
//
//@Repository
//public class OrderDAO_old implements IOrderDAO {
//    private static Logger logger = Logger.getLogger(OrderDAO_old.class);
//
//    private static final String SQL_INSERT_ORDER = "INSERT INTO main.orders " +
//                                                   "(\"from\", \"to\", price_per_km, client, status, pickup_time) "+
//                                                   "values (?,?,?,?,?,?);";
//    private static final String SQL_SELECT_ID = "SELECT * FROM main.orders " +
//                                                "WHERE id=?;";
//    private static final String SQL_SELECT_MAXID = "SELECT max(id) FROM main.orders;";
//    private static final String SQL_DELETE_ORDER = "DELETE FROM main.orders WHERE id=?;";
//    private static final String SQL_GET_ALL_ORDERS = "SELECT * FROM main.orders ORDER BY id ASC";
//    private static final String SQL_GET_FREE_ORDERS = "SELECT * FROM main.orders WHERE status=0 ORDER BY id ASC";
//    private static final String SQL_UPDATE_ORDER = "UPDATE main.orders SET ";
//
//    @Override
//    public Long insert(Order order, boolean getID) {
//        try (Connection conn = Connector.getConnection();
//             PreparedStatement prepS = conn.prepareStatement(SQL_INSERT_ORDER)) {
//            prepS.setString(1,order.getFrom());
//            prepS.setString(2,order.getTo());
//            prepS.setLong(3,order.getPrice_per_km());
//            prepS.setLong(4,order.getClient());
//            prepS.setLong(5,order.getStatus());
//            prepS.setTime(6,order.getPickup_time());
//            if (prepS.executeUpdate()>0) {
//                if (!getID) return (long) -1;
//                ResultSet rs = conn.prepareStatement(SQL_SELECT_MAXID).executeQuery();
//                rs.next();
//                Long id = rs.getLong("max");
//                return id;
//            }
//        } catch (SQLException e) {
//            logger.error("sql error in orderDAO.insert() method",e);
//        }
//        return null;
//    }
//
//    @Override
//    public Order getById(long id) {
//        try (Connection conn = Connector.getConnection();
//             PreparedStatement prepS = conn.prepareStatement(SQL_SELECT_ID)) {
//            prepS.setLong(1,id);
//            ResultSet resultSet = prepS.executeQuery();
//            Order order = new Order();
//            if (!resultSet.next()) return null;
//            order.setId(id);
//            order.setClient(resultSet.getLong("client"));
//            order.setDriver(resultSet.getLong("driver"));
//            order.setFrom(resultSet.getString("from"));
//            order.setTo(resultSet.getString("to"));
//            order.setPrice_per_km(resultSet.getLong("price_per_km"));
//            order.setStatus(resultSet.getLong("status"));
//            order.setPickup_time(resultSet.getTime("pickup_time"));
//            return order;
//        } catch (SQLException e ) {
//            logger.error("error",e);
//        }
//        return null;
//    }
//
//    @Override
//    public boolean deleteById(Long id) {
//        try (Connection conn = Connector.getConnection();
//            PreparedStatement prepS = conn.prepareStatement(SQL_DELETE_ORDER)) {
//            prepS.setLong(1,id);
//            if (prepS.executeUpdate()>0) {
//                return true;
//            }
//        } catch (SQLException e) {
//            logger.error("sql error in orderDAO.deleteById() method",e);
//        }
//        return false;
//    }
//
//    @Override
//    public boolean updateById(Long id, Properties columns) {
//        if (columns.isEmpty()) {
//            logger.warn("empty update request");
//            return true;
//        }
//        String query = SQL_UPDATE_ORDER;
//        for (String key : columns.stringPropertyNames()) {
//            query += key+"="+"?, ";
//        }
//        query = query.substring(0,query.length()-2);
//        query += " WHERE id="+id+";";
//        logger.warn(query);
//        try (Connection conn = Connector.getConnection();
//             PreparedStatement prepS = conn.prepareStatement(query)) {
//             int i = 1;
//            for (String key : columns.stringPropertyNames()) {
//                switch (key) {
//                    default:
//                        prepS.setString(i,columns.getProperty(key));
//                        i++;
//                        break;
//                    case "price_per_km":
//                    case "client":
//                    case "driver":
//                    case "status":
//                        prepS.setInt(i,Integer.parseInt(columns.getProperty(key)));
//                        i++;
//                        break;
//                    case "pickup_time":
//                        prepS.setTime(i, Time.valueOf(columns.getProperty(key)+":00"));
//                        i++;
//                }
//            }
//            logger.warn(prepS);
//            if (prepS.executeUpdate()>0) {
//                return true;
//            }
//        } catch (SQLException e) {
//            logger.error("sql error in orderDAO.update() method",e);
//        }
//        return false;
//    }
//
//    @Override
//    public ArrayList<Order> getAll() {
//        Order order;
//        ArrayList<Order> orders = new ArrayList<>();
//        try (Connection conn = Connector.getConnection();
//            PreparedStatement prepS = conn.prepareStatement(SQL_GET_ALL_ORDERS) ) {
//            ResultSet resultSet = prepS.executeQuery();
//            while (resultSet.next()){
//                order = new Order();
//                order.setId(resultSet.getLong("id"));
//                order.setFrom(resultSet.getString("from"));
//                order.setTo(resultSet.getString("to"));
//                order.setPrice_per_km(resultSet.getLong("price_per_km"));
//                order.setClient(resultSet.getLong("client"));
//                order.setDriver(resultSet.getLong("driver"));
//                order.setStatus(resultSet.getLong("status"));
//                order.setPickup_time(resultSet.getTime("pickup_time"));
//                orders.add(order);
//            }
//        } catch (SQLException e) {
//            logger.error("sql error in get all orders",e);
//        }
//        return orders;
//    }
//
//    @Override
//    public ArrayList<Order> getFreeOrders() {
//        Order order;
//        ArrayList<Order> orders = new ArrayList<>();
//        try (Connection conn = Connector.getConnection();
//             PreparedStatement prepS = conn.prepareStatement(SQL_GET_FREE_ORDERS) ) {
//            ResultSet resultSet = prepS.executeQuery();
//            while (resultSet.next()){
//                order = new Order();
//                order.setId(resultSet.getLong("id"));
//                order.setFrom(resultSet.getString("from"));
//                order.setTo(resultSet.getString("to"));
//                order.setPrice_per_km(resultSet.getLong("price_per_km"));
//                order.setClient(resultSet.getLong("client"));
//                order.setDriver(resultSet.getLong("driver"));
//                order.setStatus(resultSet.getLong("status"));
//                order.setPickup_time(resultSet.getTime("pickup_time"));
//                orders.add(order);
//            }
//        } catch (SQLException e) {
//            logger.error("sql error in get all orders",e);
//        }
//        return orders;
//    }
//
//}
