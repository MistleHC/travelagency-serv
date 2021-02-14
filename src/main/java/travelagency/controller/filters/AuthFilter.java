package travelagency.controller.filters;

import travelagency.exceptions.AuthorizationException;
import travelagency.model.User;
import travelagency.model.enums.UserRoles;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

public class AuthFilter implements Filter  {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        User user = (User) req.getSession().getAttribute("authUser");
        String path = req.getRequestURI().replace("/app", "");

        System.out.println("Auth: " + path);

        if (user != null) {
            System.out.println("Current role: " + user.getRole());
        }

        if (path.contains("styles") || path.contains("images")) {
            filterChain.doFilter(request,response);
            return;
        }

        if (Objects.isNull(user)) {
            if (!UserRoles.GUEST.getAuthorities().contains(path)) {
                throw new AuthorizationException("Unauthorized action " + path);
            } else {
                filterChain.doFilter(request,response);
            }
            return;
        }

        if (!UserRoles.valueOf(user.getRole()).getAuthorities().contains(path)) {
            throw new AuthorizationException("Unauthorized action");
        } else {
            filterChain.doFilter(request,response);
        }
    }
}
