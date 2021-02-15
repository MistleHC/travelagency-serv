package travelagency.dao;

import travelagency.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryDao extends AutoCloseable {
    List<Country> findAll();
    Optional<Country> findByName(String countryName);
}
