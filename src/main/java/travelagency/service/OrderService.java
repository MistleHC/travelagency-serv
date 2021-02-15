package travelagency.service;

import travelagency.model.Order;

import java.util.List;

public interface OrderService {
    void createOrder(long tourId, long userId);
    void deleteOrder(long orderId);
    void setPaid(long orderId);
    void setDecline(long orderId);
    List<Order> getAllByUserId(Long userId);
    List<Order> getNewOrders();
}
