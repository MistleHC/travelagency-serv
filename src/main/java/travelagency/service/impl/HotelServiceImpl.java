package travelagency.service.impl;

import travelagency.dao.DaoFactory;
import travelagency.dao.HotelDao;
import travelagency.model.HotelType;
import travelagency.service.HotelService;

import java.util.ArrayList;
import java.util.List;

public class HotelServiceImpl implements HotelService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public List<HotelType> getAll() {
        try (HotelDao hotelDao = daoFactory.createHotelDao()) {
            return hotelDao.findAll();
        } catch (Exception exception) {
            System.err.println("AutoClosable exception at HotelService!");
            return new ArrayList<>();
        }
    }
}
