package travelagency.service.impl;

import travelagency.dao.DaoFactory;
import travelagency.dao.OrderDao;
import travelagency.dao.TourDao;
import travelagency.model.Order;
import travelagency.model.Status;
import travelagency.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public boolean createOrder(long tourId, long userId) {
        try (OrderDao orderDao = daoFactory.createOrderDao();
             TourDao tourDao = daoFactory.createTourDao()) {

            Order order = Order.newBuilder()
                    .setCustomerId(userId)
                    .setTour(tourDao.findById(tourId))
                    .setStatus(new Status((long) 1, "Pending"))
                    .build();

            return orderDao.create(order);
        }
    }

    @Override
    public boolean deleteOrder(long orderId) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.delete(orderId);
        }
    }

    @Override
    public boolean setPaid(long orderId) {
        return false;
    }

    @Override
    public boolean setDecline(long orderId) {
        return false;
    }

    @Override
    public List<Order> getAllByUserId(Long userId) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findAllByCustomerId(userId);
        }
    }

    @Override
    public List<Order> getNewOrders() {
        return null;
    }
}
