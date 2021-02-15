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
    public void createOrder(long tourId, long userId) {
        try (OrderDao orderDao = daoFactory.createOrderDao();
             TourDao tourDao = daoFactory.createTourDao()) {

            Order order = Order.newBuilder()
                    .setCustomerId(userId)
                    .setTour(tourDao.findById(tourId))
                    .setStatus(new Status((long) 1, "Pending"))
                    .build();

            orderDao.create(order);
        }
    }

    @Override
    public void deleteOrder(long orderId) {

    }

    @Override
    public void setPaid(long orderId) {

    }

    @Override
    public void setDecline(long orderId) {

    }

    @Override
    public List<Order> getAllByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Order> getNewOrders() {
        return null;
    }
}
