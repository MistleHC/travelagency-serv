package travelagency.controller.command;

import travelagency.model.User;
import travelagency.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class ProfileCommand implements Command {
    private final OrderService orderService;

    public ProfileCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("authUser");

        request.getSession().setAttribute("orders", orderService.getAllByUserId(user.getId()));

        return "/customer_details.jsp";
    }
}
