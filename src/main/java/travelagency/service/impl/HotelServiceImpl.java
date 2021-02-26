package travelagency.service.impl;

import travelagency.dao.DaoFactory;
import travelagency.dao.HotelDao;
import travelagency.model.HotelType;
import travelagency.service.HotelService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class HotelServiceImpl implements HotelService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    private final static Logger logger = Logger.getLogger(HotelServiceImpl.class.getName());

    @Override
    public List<HotelType> getAll() {
        try (HotelDao hotelDao = daoFactory.createHotelDao()) {
            return hotelDao.findAll();
        } catch (Exception exception) {
            logger.info("ERROR: AutoClosable exception at HotelService!");
            return new ArrayList<>();
        }
    }
}
