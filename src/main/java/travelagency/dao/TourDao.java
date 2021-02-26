package travelagency.dao;

import travelagency.model.Tour;
import travelagency.model.TourType;

import java.util.List;

public interface TourDao extends GenericDao<Tour> {
    Tour findById(Long id);
    Tour findByName(String name);
    List<Tour> findAllByCountry(String country);
    List<Tour> findAllByHotelTypeName(String hotel);
    List<Tour> findAllByCountryAndHotelTypeName(String country, String hotel);
    List<TourType> getTourTypes();
    TourType getTourTypeByName(String name);
    boolean setHot(Long id, Boolean condition);
}
