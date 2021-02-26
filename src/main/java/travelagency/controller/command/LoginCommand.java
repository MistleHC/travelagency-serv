package travelagency.controller.command;

import travelagency.model.User;
import travelagency.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

public class LoginCommand implements Command {
    private final UserService userService;

    private final static Logger logger = Logger.getLogger(LoginCommand.class.getName());

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null && password == null && request.getSession().getAttribute("error") != null) {
            request.getSession().setAttribute("error", null);
        }

        if (email == null || email.equals("") || password == null || password.equals("")) {
            return "/login.jsp";
        }

        User currentUser = userService.findUserByEmail(email);

        if (currentUser == null) {
            request.getSession().setAttribute("error", "Wrong email or password");
            return "/login.jsp";
        }

        if (currentUser.getPassword().equals(password) && currentUser.getEmail().equals(email)) {
            request.getSession().setAttribute("authUser", currentUser);

            logger.info("Logged in user is: " + request.getSession().getAttribute("authUser").toString());
        }

        return "redirect:/home";
    }
}
