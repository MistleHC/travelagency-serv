package travelagency.controller.command;

import travelagency.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("authUser");

        if (Objects.nonNull(user)) {
            request.getSession().setAttribute("authUser", null);
        }

        return "redirect:/home";
    }
}
