package travelagency.dao;

import travelagency.model.Tour;

import java.util.List;
import java.util.Optional;

public interface TourDao extends GenericDao<Tour> {
    Optional<Tour> findById(Long id);
    List<Tour> findAllByCountry(String country);
    List<Tour> findAllByHotelType_Name(String hotel);
    List<Tour> findAllByCountryAndHotelType_Name(String country, String hotel);
    boolean setHot(Long id,Boolean condition);
}
