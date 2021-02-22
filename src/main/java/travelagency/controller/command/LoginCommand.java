package travelagency.controller.command;

import travelagency.model.User;
import travelagency.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.equals("") || password == null || password.equals("")) {
            return "/login.jsp";
        }

        User currentUser = userService.findUserByEmail(email);

        if (currentUser.getPassword().equals(password) && currentUser.getEmail().equals(email)) {
            request.getSession().setAttribute("authUser", currentUser);

            System.out.println("Logged in user is: " + request.getSession().getAttribute("authUser").toString());
        } else {
            request.getSession().setAttribute("error", "Wrong email or password");
        }

        return "redirect:/home";
    }
}
