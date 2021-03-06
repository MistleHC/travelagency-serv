package travelagency.dao;

import travelagency.model.Tour;
import travelagency.model.TourType;

import java.util.List;

public interface TourDao extends GenericDao<Tour> {
    Tour findById(Long id);
    Tour findByName(String name);
    List<Tour> findAll(int currentPage, int recordsPerPage);
    List<Tour> findAllByCountry(String country, int currentPage, int recordsPerPage);
    List<Tour> findAllByHotelTypeName(String hotel, int currentPage, int recordsPerPage);
    List<Tour> findAllByCountryAndHotelTypeName(String country, String hotel, int currentPage, int recordsPerPage);
    List<TourType> getTourTypes();
    TourType getTourTypeByName(String name);
    boolean setHot(Long id, Boolean condition);
    Integer getNumberOfRows();
}
