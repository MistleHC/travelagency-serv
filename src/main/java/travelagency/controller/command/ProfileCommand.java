package travelagency.controller.command;

import travelagency.model.User;
import travelagency.service.OrderService;
import travelagency.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class ProfileCommand implements Command {
    private final OrderService orderService;
    private final UserService userService;

    public ProfileCommand(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String userFullName = request.getParameter("userFullName");
        String userDescription = request.getParameter("userDescription");
        User user = (User) request.getSession().getAttribute("authUser");

        if (Objects.nonNull(userFullName) && !userFullName.equals("") && Objects.nonNull(userDescription) && !userDescription.equals("")) {
            user.setFullName(userFullName);
            user.setAboutMe(userDescription);

            if (userService.updateUser(user)) {
                request.getSession().setAttribute("authUser", user);

                return "redirect:/profile";
            } else {
                user = (User) request.getSession().getAttribute("authUser");
            }
        }

        request.getSession().setAttribute("orders", orderService.getAllByUserId(user.getId()));

        return "/customer_details.jsp";
    }
}
