package travelagency.service;

import travelagency.controller.dto.TourCreationDto;
import travelagency.controller.dto.TourFilterDto;
import travelagency.model.Tour;
import travelagency.model.TourType;

import java.util.List;

public interface TourService {
    List<Tour> getAll(int currentPage, int recordsPerPage);
    Tour getById(long tourId);
    List<Tour> getAllByFilter(TourFilterDto filter, int currentPage, int recordsPerPage);
    List<TourType> getTourTypes();
    void setHot(Long tourId);
    void setNotHot(Long tourId);
    void deleteById(Long tourId);
    boolean saveNewTour(TourCreationDto tourCreationDto);
    Integer getNumberOfRows();
}
