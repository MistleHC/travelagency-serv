package travelagency.service.impl;

import travelagency.controller.dto.TourCreationDto;
import travelagency.controller.dto.TourFilterDto;
import travelagency.dao.DaoFactory;
import travelagency.dao.TourDao;
import travelagency.dao.UserDao;
import travelagency.model.Tour;
import travelagency.model.TourType;
import travelagency.service.TourService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TourServiceImpl implements TourService {
    DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public List<Tour> getAll() {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.findAll();
        }
    }

    @Override
    public Tour getById(long tourId) {
        return null;
    }

    @Override
    public List<Tour> getAllByFilter(TourFilterDto filter) {
        List<Tour> tours;
        try (TourDao tourDao = daoFactory.createTourDao()) {
            if (filter.getHotel().equals("") && !filter.getCountry().equals("")) {
                tours = tourDao.findAllByCountry(filter.getCountry());
            } else if (filter.getCountry().equals("") && !filter.getHotel().equals("")) {
                tours = tourDao.findAllByHotelType_Name(filter.getHotel());
            } else if (!filter.getCountry().equals("") && !filter.getCountry().equals("")) {
                tours = tourDao.findAllByCountryAndHotelType_Name(filter.getCountry(), filter.getHotel());
            } else {
                tours = getAll();
            }

            return filterTours(tours, filter);
        }
    }

    @Override
    public List<TourType> getTourTypes() {
        return null;
    }

    @Override
    public void setHot(Long tourId) {

    }

    @Override
    public void setNotHot(Long tourId) {

    }

    @Override
    public void deleteById(Long tourId) {

    }

    @Override
    public Tour saveNewTour(TourCreationDto tourCreationDto) {
        return null;
    }

    private List<Tour> filterTours(List<Tour> list, TourFilterDto filter) {
        List<Tour> tours = list;
        if (!filter.getLowerPrice().equals("")) {
            tours = tours.stream().filter(t -> t.getPrice() > Integer.parseInt(filter.getLowerPrice())).collect(Collectors.toList());
        }
        if (!filter.getHigherPrice().equals("")) {
            tours = tours.stream().filter(t -> t.getPrice() < Integer.parseInt(filter.getHigherPrice())).collect(Collectors.toList());
        }
        if (!filter.getLowerGroup().equals("")) {
            tours = tours.stream().filter(t -> t.getPeoples() < Integer.parseInt(filter.getLowerGroup())).collect(Collectors.toList());
        }
        tours.sort((a, b) -> Boolean.compare(a.isHot(), b.isHot()));
        Collections.reverse(tours);

        return tours;
    }
}
