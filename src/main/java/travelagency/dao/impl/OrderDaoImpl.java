package travelagency.dao.impl;

import travelagency.constants.SQLConstants;
import travelagency.dao.OrderDao;
import travelagency.model.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private Connection connection;

    public OrderDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Order> findAllByStatusTitle(String statusTitle) {
        return null;
    }

    @Override
    public List<Order> findAllByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public boolean create(Order order) {
        try (Statement st = connection.createStatement()) {
            int affectedRows = st.executeUpdate(String.format(SQLConstants.INSERT_NEW_ORDER,
                                                                order.getCustomer(),
                                                                order.getTour().getId(),
                                                                order.getStatus().getId()));
            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            return true;
        } catch (SQLException e) {
            System.err.println("Couldn't insert new order " + e.getMessage());
            return false;
        }
    }

    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public boolean update(Order entity) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
