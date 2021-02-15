package travelagency.controller.command;

import travelagency.model.User;
import travelagency.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class OrderCommand implements Command {
    private final OrderService orderService;

    public OrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String newOrderTourId = request.getParameter("tourid");

        User user = (User) request.getSession().getAttribute("authUser");

        if (Objects.nonNull(newOrderTourId) && !newOrderTourId.equals("")) {
            orderService.createOrder(Long.parseLong(newOrderTourId), user.getId());
        }

        return "redirect:/profile";
    }
}
