package travelagency.service;

import travelagency.model.Order;

import java.util.List;

public interface OrderService {
    boolean createOrder(long tourId, long userId);
    boolean deleteOrder(long orderId);
    boolean setPaid(long orderId);
    boolean setDecline(long orderId);
    List<Order> getAllByUserId(Long userId);
    List<Order> getNewOrders();
}
