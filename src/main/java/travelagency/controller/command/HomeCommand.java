package travelagency.controller.command;

import travelagency.controller.dto.TourFilterDto;
import travelagency.service.TourService;

import javax.servlet.http.HttpServletRequest;

public class HomeCommand implements Command {
    private final TourService tourService;

    public HomeCommand(TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String country = request.getParameter("country");
        String hotel = request.getParameter("hotel");
        String lowerprice = request.getParameter("lowerprice");
        String higherprice = request.getParameter("higherprice");
        String lowergroup = request.getParameter("lowergroup");

        TourFilterDto tourFilterDto = TourFilterDto.newBuilder()
                                                   .setCountry(country)
                                                   .setHotel(hotel)
                                                   .setLowerPrice(lowerprice)
                                                   .setHigherPrice(higherprice)
                                                   .setLowerGroup(lowergroup)
                                                   .build();
        tourFilterDto.changeDefaultValues();

        request.getSession().setAttribute("tours", tourService.getAllByFilter(tourFilterDto));










        return "/index.jsp";
    }
}
