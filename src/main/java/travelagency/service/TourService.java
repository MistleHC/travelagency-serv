package travelagency.service;

import travelagency.controller.dto.TourCreationDto;
import travelagency.controller.dto.TourFilterDto;
import travelagency.model.Tour;
import travelagency.model.TourType;

import java.util.List;

public interface TourService {
    List<Tour> getAll();
    Tour getById(long tourId);
    List<Tour> getAllByFilter(TourFilterDto filter);
    List<TourType> getTourTypes();
    void setHot(Long tourId);
    void setNotHot(Long tourId);
    void deleteById(Long tourId);
    boolean saveNewTour(TourCreationDto tourCreationDto);
}
