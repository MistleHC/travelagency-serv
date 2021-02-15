package travelagency.dao;

import travelagency.model.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    List<Order> findAllByStatusTitle(String statusTitle);
    List<Order> findAllByCustomerId(Long customerId);
    boolean updateOrderStatus(Long orderId, Long statusId);
}
