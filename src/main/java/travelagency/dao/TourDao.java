package travelagency.dao;

import travelagency.model.Tour;
import travelagency.model.TourType;

import java.util.List;

public interface TourDao extends GenericDao<Tour> {
    Tour findById(Long id);
    List<Tour> findAllByCountry(String country);
    List<Tour> findAllByHotelType_Name(String hotel);
    List<Tour> findAllByCountryAndHotelType_Name(String country, String hotel);
    List<TourType> getTourTypes();
    TourType getTourTypeByName(String name);
    boolean setHot(Long id, Boolean condition);
}
