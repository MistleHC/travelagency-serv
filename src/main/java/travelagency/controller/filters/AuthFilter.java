package travelagency.controller.filters;

import travelagency.exceptions.AuthorizationException;
import travelagency.model.User;
import travelagency.model.enums.UserRoles;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class AuthFilter implements Filter  {
    private final static Logger logger = Logger.getLogger(AuthFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        User user = (User) req.getSession().getAttribute("authUser");
        String path = req.getRequestURI().replace("/app", "");

        if (user != null) {
            logger.info("Path: " + path + "; Current role: " + user.getRole());
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
