package travelagency.service.impl;

import travelagency.controller.dto.TourCreationDto;
import travelagency.controller.dto.TourFilterDto;
import travelagency.dao.DaoFactory;
import travelagency.dao.HotelDao;
import travelagency.dao.TourDao;
import travelagency.model.Tour;
import travelagency.model.TourType;
import travelagency.service.TourService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        try (TourDao tourDao = daoFactory.createTourDao()) {
            return tourDao.getTourTypes();
        }
    }

    @Override
    public void setHot(Long tourId) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            tourDao.setHot(tourId, true);
        }
    }

    @Override
    public void setNotHot(Long tourId) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            tourDao.setHot(tourId, false);
        }
    }

    @Override
    public void deleteById(Long tourId) {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            tourDao.delete(tourId);
        }
    }

    @Override
    public boolean saveNewTour(TourCreationDto tourCreationDto) {
        try (TourDao tourDao = daoFactory.createTourDao();
             HotelDao hotelDao = daoFactory.createHotelDao()) {

            Tour tour = Tour.newBuilder()
                    .setName(tourCreationDto.getTourName())
                    .setDescription(tourCreationDto.getTourDescription())
                    .setCountry(tourCreationDto.getTourCountry())
                    .setPeoples(tourCreationDto.getTourSize())
                    .setPrice(tourCreationDto.getTourPrice())
                    .setHot(false)
                    .setHotelType(hotelDao.getByName(tourCreationDto.getTourHotel()))
                    .setTourType(tourDao.getTourTypeByName(tourCreationDto.getTourType()))
                    .build();

            return tourDao.create(tour);
        } catch (Exception exception) {
            System.err.println("Error with AutoClosable at TourService!");
            return false;
        }
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
