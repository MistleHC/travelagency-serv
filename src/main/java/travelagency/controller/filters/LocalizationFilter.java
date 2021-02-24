package travelagency.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LocalizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest.getParameter("lang") != null) {
            ((HttpServletRequest)servletRequest).getSession().setAttribute("lang", servletRequest.getParameter("lang"));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
