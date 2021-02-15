package travelagency.dao;

import travelagency.model.HotelType;

import java.util.List;

public interface HotelDao extends AutoCloseable {
    List<HotelType> findAll();
    HotelType getByName(String name);
}
