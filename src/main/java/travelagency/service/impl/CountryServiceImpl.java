package travelagency.service.impl;

import travelagency.dao.CountryDao;
import travelagency.dao.DaoFactory;
import travelagency.model.Country;
import travelagency.service.CountryService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CountryServiceImpl implements CountryService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    private final static Logger logger = Logger.getLogger(CountryServiceImpl.class.getName());

    @Override
    public List<Country> getAll() {
        try (CountryDao countryDao = daoFactory.createCountryDao()) {
            return countryDao.findAll();
        } catch (Exception exception) {
            logger.info("ERROR: AutoClosable exception at CountryService!");
            return new ArrayList<>();
        }
    }

    @Override
    public boolean existsByName(String countryName) {
        return false;
    }
}
