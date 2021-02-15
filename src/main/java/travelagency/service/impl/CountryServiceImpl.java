package travelagency.service.impl;

import travelagency.dao.CountryDao;
import travelagency.dao.DaoFactory;
import travelagency.model.Country;
import travelagency.service.CountryService;

import java.util.ArrayList;
import java.util.List;

public class CountryServiceImpl implements CountryService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public List<Country> getAll() {
        try (CountryDao countryDao = daoFactory.createCountryDao()) {
            return countryDao.findAll();
        } catch (Exception exception) {
            System.err.println("AutoClosable exception at CountryService!");
            return new ArrayList<>();
        }
    }

    @Override
    public boolean existsByName(String countryName) {
        return false;
    }
}
