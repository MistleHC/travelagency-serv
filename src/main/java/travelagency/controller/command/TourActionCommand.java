package travelagency.controller.command;

import travelagency.controller.dto.TourCreationDto;
import travelagency.controller.validator.TourCreationValidator;
import travelagency.exceptions.ValidationException;
import travelagency.service.TourService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class TourActionCommand implements Command {
    private final TourService tourService;

    public TourActionCommand(TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String tourName = request.getParameter("tourName");
        String tourDescription = request.getParameter("tourDescription");
        String tourType = request.getParameter("tourType");
        String tourCountry = request.getParameter("tourCountry");
        String tourSize = request.getParameter("tourSize");
        String tourHotel = request.getParameter("tourHotel");
        String tourPrice = request.getParameter("tourPrice");

        String deleteTourId = request.getParameter("deltourid");
        String hotTourId = request.getParameter("hottourid");
        String deHotTourId = request.getParameter("dehottourid");

        if (Objects.nonNull(deHotTourId)) {
            tourService.setNotHot(Long.parseLong(deHotTourId));
            return "redirect:/home";
        }

        if (Objects.nonNull(hotTourId)) {
            tourService.setHot(Long.parseLong(hotTourId));
            return "redirect:/home";
        }

        if (Objects.nonNull(deleteTourId)) {
            tourService.deleteById(Long.parseLong(deleteTourId));
            return "redirect:/home";
        }

        if (tourSize != null && !tourSize.equals("") && tourPrice != null && !tourPrice.equals("")) {
            TourCreationDto tourCreationDto = TourCreationDto.newBuilder()
                    .setTourName(tourName)
                    .setTourDescription(tourDescription)
                    .setTourType(tourType)
                    .setTourCountry(tourCountry)
                    .setTourSize(Long.parseLong(tourSize))
                    .setTourHotel(tourHotel)
                    .setTourPrice(Long.parseLong(tourPrice))
                    .build();

            try {
                TourCreationValidator.validate(tourCreationDto);
            } catch (ValidationException ex) {
                request.getSession().setAttribute("error", "Enter correct data!");
                return "redirect:/home";
            }

            tourService.saveNewTour(tourCreationDto);
        }

        return "redirect:/home";
    }
}
