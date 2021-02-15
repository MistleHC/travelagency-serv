package travelagency.dao.impl;

import travelagency.constants.SQLConstants;
import travelagency.dao.OrderDao;
import travelagency.dao.mapper.TourMapper;
import travelagency.model.Order;
import travelagency.model.Status;
import travelagency.model.Tour;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        List<Order> orders = new ArrayList<>();

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(String.format(SQLConstants.GET_ORDERS_BY_CUSTOMER_ID, customerId));

            TourMapper tourMapper = new TourMapper();

            while (rs.next()) {
                Tour tour = tourMapper.extractFromResultSet(rs);

                Status status = new Status(rs.getLong("status_id"), rs.getString("status_title"));

                Order order = Order.newBuilder()
                        .setId(rs.getLong("order_id"))
                        .setCustomerId(rs.getLong("order_customer_id"))
                        .setTour(tour)
                        .setStatus(status)
                        .build();

                orders.add(order);
            }

            return orders;
        } catch (SQLException e) {
            System.err.println("Couldn't get user orders " + e.getMessage());
            return orders;
        }
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
        try (Statement st = connection.createStatement()){
            int affectedRows = st.executeUpdate(String.format(SQLConstants.DELETE_ORDER_BY_ID, id));

            if (affectedRows == 0) {
                throw new SQLException("Deleting order failed, no rows affected.");
            }

            return true;
        } catch (SQLException e) {
            System.err.println("Couldn't delete order " + e.getMessage());
            return false;
        }
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
