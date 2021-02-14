package travelagency.service;

import travelagency.model.Country;

import java.util.List;

public interface CountryService {
    List<Country> getAll();
    boolean existsByName(String countryName);
}
