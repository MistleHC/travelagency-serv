package travelagency.dao.impl;

import travelagency.constants.SQLConstants;
import travelagency.dao.OrderDao;
import travelagency.dao.mapper.TourMapper;
import travelagency.model.Order;
import travelagency.model.Status;
import travelagency.model.Tour;
import travelagency.model.User;

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
        List<Order> orders = new ArrayList<>();

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(String.format(SQLConstants.GET_ORDERS_BY_STATUS_TITLE, statusTitle));

            return MapOrderRelativeEntities(orders, rs);
        } catch (SQLException e) {
            System.err.println("Couldn't get pending orders " + e.getMessage());
            return orders;
        }
    }

    @Override
    public List<Order> findAllByCustomerId(Long customerId) {
        List<Order> orders = new ArrayList<>();

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(String.format(SQLConstants.GET_ORDERS_BY_CUSTOMER_ID, customerId));

            return MapOrderRelativeEntities(orders, rs);
        } catch (SQLException e) {
            System.err.println("Couldn't get user orders " + e.getMessage());
            return orders;
        }
    }

    @Override
    public boolean updateOrderStatus(Long orderId, Long statusId) {
        try (Statement st = connection.createStatement()) {
            int affectedRows = st.executeUpdate(String.format(SQLConstants.UPDATE_ORDER_STATUS,
                                                                            statusId,
                                                                            orderId));
            if (affectedRows == 0) {
                throw new SQLException("Updating order status failed, no rows affected.");
            }

            return true;
        } catch (SQLException e) {
            System.err.println("Couldn't update order " + e.getMessage());
            return false;
        }
    }

    private List<Order> MapOrderRelativeEntities(List<Order> orders, ResultSet rs) throws SQLException {
        TourMapper tourMapper = new TourMapper();

        while (rs.next()) {
            Tour tour = tourMapper.extractFromResultSet(rs);

            Status status = new Status(rs.getLong("status_id"), rs.getString("status_title"));

            User user = User.newBuilder()
                    .setId(rs.getLong("user_id"))
                    .setEmail(rs.getString("user_email"))
                    .setName(rs.getString("user_name"))
                    .build();

            Order order = Order.newBuilder()
                    .setId(rs.getLong("order_id"))
                    .setCustomerId(rs.getLong("order_customer_id"))
                    .setCustomer(user)
                    .setTour(tour)
                    .setStatus(status)
                    .build();

            orders.add(order);
        }

        return orders;
    }

    @Override
    public boolean create(Order order) {
        System.out.println();

        try (Statement st = connection.createStatement()) {
            int affectedRows = st.executeUpdate(String.format(SQLConstants.INSERT_NEW_ORDER,
                                                                order.getCustomerId(),
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
