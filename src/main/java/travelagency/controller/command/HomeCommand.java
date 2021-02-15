package travelagency.controller.command;

import travelagency.controller.dto.TourFilterDto;
import travelagency.service.CountryService;
import travelagency.service.HotelService;
import travelagency.service.TourService;

import javax.servlet.http.HttpServletRequest;

public class HomeCommand implements Command {
    private final TourService tourService;
    private final CountryService countryService;
    private final HotelService hotelService;

    public HomeCommand(TourService tourService, CountryService countryService, HotelService hotelService) {
        this.tourService = tourService;
        this.countryService = countryService;
        this.hotelService = hotelService;
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
        request.getSession().setAttribute("countries", countryService.getAll());
        request.getSession().setAttribute("hotels", hotelService.getAll());
        request.getSession().setAttribute("tourTypes", tourService.getTourTypes());

        return "/home_page.jsp";
    }
}
