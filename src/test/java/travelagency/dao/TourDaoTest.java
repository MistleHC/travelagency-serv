package travelagency.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import travelagency.controller.dto.TourCreationDto;
import travelagency.model.Tour;
import travelagency.model.TourType;
import travelagency.service.TourService;
import travelagency.service.impl.TourServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TourDaoTest {
    DaoFactory daoFactory = DaoFactory.getInstance();

    private final List<Long> tourIds = new ArrayList<>();

    @Before
    public void init() { }

    @Test
    public void testFindById() {
        createObject("FindByIdTest");
        try (TourDao tourDao = daoFactory.createTourDao()) {
            Tour tour = tourDao.findByName("FindByIdTest");

            assertEquals(tour, tourDao.findById(tour.getId()));
        }
    }

    @Test
    public void testFindAllByCountry() {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            List<Tour> tours = tourDao.findAllByCountry("Japan", 1, 999);
            assertNotEquals(tourDao.findAll(1, 999).size(), tours);

            for (Tour t : tours) {
               assertEquals("Japan", t.getCountry());
            }
        }
    }

    @Test
    public void testFindByHotelTypeName() {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            List<Tour> tours = tourDao.findAllByHotelTypeName("Resort", 1, 999);
            assertNotEquals(tourDao.findAll(1, 999).size(), tours);

            for (Tour t : tours) {
                assertEquals("Resort", t.getHotelType().getName());
            }
        }
    }

    @Test
    public void testFindByHotelAndTourType() {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            List<Tour> tours = tourDao.findAllByCountryAndHotelTypeName("Japan", "Resort", 1, 999);
            assertNotEquals(tourDao.findAll(1, 999).size(), tours);

            for (Tour t : tours) {
                assertEquals("Japan", t.getCountry());
                assertEquals("Resort", t.getHotelType().getName());
            }
        }
    }

    @Test
    public void testGetTourTypesAndGetByNameShouldContainSameTypes() {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            List<TourType> tourTypes = tourDao.getTourTypes();

            for (TourType t : tourTypes) {
                assertEquals(t.getName(), tourDao.getTourTypeByName(t.getName()).getName());
            }
        }
    }

    @Test
    public void testSetIsHotSettsProperStatus() {
        Long id;
        createObject("HotTest");

        try (TourDao tourDao = daoFactory.createTourDao()) {
            id = tourDao.findByName("HotTest").getId();

            tourDao.setHot(id, true);
            assertTrue(tourDao.findById(id).isHot());
            tourDao.setHot(id, false);
            assertFalse(tourDao.findById(id).isHot());
        }
    }

    @Test
    public void testDeletingTourObject() {
        Long id;
        createObject("DeleteTest");

        try (TourDao tourDao = daoFactory.createTourDao()) {
            int initialSize = tourDao.findAll(1, 999).size();
            System.out.println(tourDao.findAll(1, 999).size());
            id = tourDao.findByName("DeleteTest").getId();
            tourDao.delete(id);
            tourIds.remove(id);

            assertEquals(initialSize - 1, tourDao.findAll(1, 999).size());
        }
    }

    @After
    public void destroy() {
        try (TourDao tourDao = daoFactory.createTourDao()) {
            for (Long l : tourIds) {
                tourDao.delete(l);
            }
        }
    }

    private void createObject(String name) {
        TourService tourService = new TourServiceImpl();
        TourCreationDto tourCreationDto = TourCreationDto.newBuilder()
                .setTourName(name)
                .setTourDescription("Desc")
                .setTourCountry("Japan")
                .setTourSize((long) 12)
                .setTourPrice((long) 20000)
                .setTourHotel("Lodge")
                .setTourType("Vacation")
                .build();

        tourService.saveNewTour(tourCreationDto);

        try (TourDao tourDao = daoFactory.createTourDao()) {
            tourIds.add(tourDao.findByName(tourCreationDto.getTourName()).getId());
        }
    }

}
