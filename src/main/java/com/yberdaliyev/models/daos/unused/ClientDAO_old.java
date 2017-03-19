//package com.yberdaliyev.models.daos.unused;
//
//import com.yberdaliyev.models.connectors.Connector;
//import com.yberdaliyev.models.daos.IClientDAO;
//import com.yberdaliyev.models.daos.IUserDAO;
//import com.yberdaliyev.models.pojos.Client;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Properties;
//
//@Repository//
//public class ClientDAO_old {
//    private IUserDAO userDAO;
//
//    @Autowired
//    public ClientDAO_old(IUserDAO userDAO) {
//        this.userDAO = userDAO;
//    }
//
//    private static Logger logger = Logger.getLogger(ClientDAO_old.class);
//
//    private static final String SQL_INSERT_CLIENT = "INSERT INTO main.clients " +
//            "(firstname,lastname,patronymic,birthdate,login,email, date_registered) "+
//            "values (?,?,?,?,?,?,?)";
//    private static final String SQL_UPDATE_ORDER = "UPDATE main.clients " +
//            "SET \"order\"=? WHERE id=?;";
//    private static final String SQL_SELECT_ID = "SELECT * FROM main.clients " +
//            "WHERE id=?";
//    private static final String SQL_GET_ALL_CLIENTS = "SELECT * FROM main.clients ORDER BY id ASC";
//    private static final String SQL_UPDATE_CLIENT = "UPDATE main.clients SET ";
//    private static final String SQL_DELETE_CLIENT = "DELETE FROM main.clients WHERE id=?;";
//    private static final String SQL_INCREMENT_ORDERS_COUNT = "UPDATE main.clients SET orders_amount=orders_amount+1 WHERE id=?;";
//    private static final String SQL_SELECT_MAXID = "SELECT max(id) FROM main.clients;";
//
//    @Override
//    public Client getById(long id) {
//        try (Connection conn = Connector.getConnection();
//             PreparedStatement prepS = conn.prepareStatement(SQL_SELECT_ID)) {
//            prepS.setLong(1,id);
//            ResultSet resultSet = prepS.executeQuery();
//            Client client = new Client();
//            resultSet.next();
//            userDAO.setFields(client,resultSet);
//            return client;
//        } catch (SQLException e ) {
//            logger.error("error",e);
//        }
//        return null;
//    }
//
//    @Override
//    public Long insert(Client client, boolean getID) {
//        try (PreparedStatement prepS = userDAO.insert(SQL_INSERT_CLIENT, client) ){
//            prepS.setDate(10,client.getDate_registered());
//            if (prepS.executeUpdate() > 0) {
//                if (!getID) return (long) -1;
//                Connection conn = Connector.getConnection();
//                ResultSet rs = conn.prepareStatement(SQL_SELECT_MAXID).executeQuery();
//                rs.next();
//                Long id = rs.getLong("max");
//                return id;
//            }
//        } catch (SQLException e) {
//            logger.error("sql error in insert client method",e);
//        }
//        return null;
//    }
//
//
//    @Override
//    public ArrayList<Client> getAll() {
//        Client client;
//        ArrayList<Client> clients = new ArrayList<>();
//        try (Connection conn = Connector.getConnection();
//             PreparedStatement prepS = conn.prepareStatement(SQL_GET_ALL_CLIENTS) ) {
//            ResultSet resultSet = prepS.executeQuery();
//            while (resultSet.next()){
//                client = new Client();
//                client.setId(resultSet.getLong("id"));
//                client.setFirstname(resultSet.getString("firstname"));
//                client.setLastname(resultSet.getString("lastname"));
//                client.setPatronymic(resultSet.getString("patronymic"));
//                client.setDate_registered(resultSet.getDate("date_registered"));
//                client.setOrders_amount(resultSet.getLong("orders_amount"));
//                client.setBirthdate(resultSet.getDate("birthdate"));
//                client.setLogin(resultSet.getString("login"));
//                client.setEmail(resultSet.getString("email"));
//                client.setOrder(resultSet.getLong("order"));
//                clients.add(client);
//            }
//        } catch (SQLException e) {
//            logger.error("sql error in get all clients",e);
//        }
//        return clients;
//    }
//
//    @Override
//    public boolean updateById(Long id, Properties columns) {
//        if (columns.isEmpty()) {
//            logger.warn("empty update client request");
//            return true;
//        }
//        String query = SQL_UPDATE_CLIENT;
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
//                    case "orders_amount":
//                    case "\"order\"":
//                        prepS.setInt(i,Integer.parseInt(columns.getProperty(key)));
//                        i++;
//                        break;
//                    case "date_registered":
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
//            logger.error("sql error in clientDAO.updatebyId() method",e);
//        }
//        return false;
//    }
//
//    @Override
//    public boolean updateOrder(Long clientid, Long orderid) {
//        try (Connection conn = Connector.getConnection();
//             PreparedStatement prepS = conn.prepareStatement(SQL_UPDATE_ORDER) ){
//            prepS.setLong(1, orderid);
//            prepS.setLong(2, clientid);
//            if (prepS.executeUpdate() > 0) return true;
//        } catch (SQLException e) {
//            logger.error("sql error in update client method",e);
//        }
//        return false;
//    }
//
//    @Override
//    public boolean deleteById(Long id) {
//        try (Connection conn = Connector.getConnection();
//             PreparedStatement prepS = conn.prepareStatement(SQL_DELETE_CLIENT)) {
//            prepS.setLong(1,id);
//            if (prepS.executeUpdate()>0) {
//                return true;
//            }
//        } catch (SQLException e) {
//            logger.error("sql error in clientDAO.deleteById() method",e);
//        }
//        return false;
//    }
//
//    @Override
//    public boolean increaseOrdersCount(Long client_id) {
//        try (Connection conn = Connector.getConnection();
//             PreparedStatement prepS = conn.prepareStatement(SQL_INCREMENT_ORDERS_COUNT)) {
//            prepS.setLong(1,client_id);
//            if (prepS.executeUpdate()>0) {
//                return true;
//            }
//        } catch (SQLException e) {
//            logger.error("sql error in clientDAO.increaseOrdersCount() method",e);
//        }
//        return false;
//    }
//}
