package travelagency.controller.command;

import travelagency.model.User;
import travelagency.model.enums.UserRoles;
import travelagency.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command  {
    private final UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean success;
        User user;

        System.out.println(name + " " + email + " " + password);

        if (name == null && email == null && password == null && request.getSession().getAttribute("error") != null) {
            request.getSession().setAttribute("error", null);
        }

        if (name == null && email == null && password == null) {
            return "/registration.jsp";
        }

        if (email == null || email.equals("")) {
            request.getSession().setAttribute("error", "Email should be specified!");
            return "/registration.jsp";
        }

        if (password == null || password.equals("")) {
            request.getSession().setAttribute("error", "Password should be specified!");
            return "/registration.jsp";
        }

        if (name == null || name.equals("")) {
            request.getSession().setAttribute("error", "Name should be specified!");
            return "/registration.jsp";
        }

        if (userService.findUserByEmail(email) != null) {
            request.getSession().setAttribute("error", "This email is already registered!");
            return "/registration.jsp";
        } else {
            user = User.newBuilder()
                            .setName(name)
                            .setEmail(email)
                            .setPassword(password)
                            .setRoles(UserRoles.CUSTOMER)
                            .build();
            success = userService.registerUser(user);
        }


        if (success) {
            request.getSession().setAttribute("authUser", userService.findUserByEmail(email));

            if (CommandUtility.checkUserIsLogged(request, user.getEmail())) {
                return "/WEB-INF/error.jsp";
            }
        }

        System.out.println("Logged in user is: " + request.getSession().getAttribute("authUser").toString());

        return "redirect:/home";
    }
}
