package travelagency.controller;

import travelagency.controller.command.*;
import travelagency.service.impl.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig){
        servletConfig.getServletContext().setAttribute("loggedUsers", new HashSet<String>());

        commands.put("logout", new LogoutCommand());
        commands.put("login", new LoginCommand(new UserServiceImpl()));
        commands.put("registration", new RegistrationCommand(new UserServiceImpl()));
        commands.put("exception" , new ExceptionCommand());
        commands.put("home", new HomeCommand(new TourServiceImpl(), new CountryServiceImpl(), new HotelServiceImpl()));
        commands.put("tour/create", new TourActionCommand(new TourServiceImpl()));
        commands.put("tour/delete", new TourActionCommand(new TourServiceImpl()));
        commands.put("tour/hot", new TourActionCommand(new TourServiceImpl()));
        commands.put("tour/de-hot", new TourActionCommand(new TourServiceImpl()));
        commands.put("order", new OrderCommand(new OrderServiceImpl()));
        commands.put("order/delete", new OrderCommand(new OrderServiceImpl()));
        commands.put("profile", new ProfileCommand(new OrderServiceImpl(), new UserServiceImpl()));
        commands.put("manage", new ManagementCommand(new OrderServiceImpl()));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/app/" , "");
        Command command = commands.getOrDefault(path , (r)->"/index.jsp)");
        System.out.println(command.getClass().getName());
        String page = command.execute(request);

        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", "/app"));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
