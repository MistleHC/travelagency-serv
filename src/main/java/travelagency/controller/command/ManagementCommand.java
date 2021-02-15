package travelagency.controller.command;

import travelagency.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class ManagementCommand implements Command {
    private final OrderService orderService;

    public ManagementCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String paidOrderId = request.getParameter("paidOrderId");
        String declineOrderId = request.getParameter("declineOrderId");
        String deleteOrderId = request.getParameter("deleteOrderId");

        if (Objects.nonNull(paidOrderId) && !paidOrderId.equals("")) {
            orderService.setPaid(Long.parseLong(paidOrderId));
        }

        if (Objects.nonNull(declineOrderId) && !declineOrderId.equals("")) {
            orderService.setDecline(Long.parseLong(declineOrderId));
        }

        if (Objects.nonNull(deleteOrderId) && !deleteOrderId.equals("")) {
            orderService.deleteOrder(Long.parseLong(deleteOrderId));
        }

        request.getSession().setAttribute("orders", orderService.getNewOrders());

        return "/management.jsp";
    }
}
