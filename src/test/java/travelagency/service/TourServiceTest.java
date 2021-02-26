package travelagency.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import travelagency.controller.dto.TourCreationDto;
import travelagency.controller.dto.TourFilterDto;
import travelagency.dao.DaoFactory;
import travelagency.dao.TourDao;
import travelagency.service.impl.TourServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TourServiceTest {
    private final TourServiceImpl tourService = new TourServiceImpl();
    DaoFactory daoFactory = DaoFactory.getInstance();

    private final List<Long> tourIds = new ArrayList<>();

    @Before
    public void init() { }

    @Test
    public void testSettingTourAsHot() {
        Long id;
        createObject("HotTest");

        try (TourDao tourDao = daoFactory.createTourDao()) {
            id = tourDao.findByName("HotTest").getId();
        }

        boolean initialState = tourService.getById(id).isHot();
        tourService.setHot(id);

        assertNotEquals(initialState, tourService.getById(id).isHot());
    }

    @Test
    public void testSettingTourAsNotHot() {
        Long id;
        createObject("NotHotTest");

        try (TourDao tourDao = daoFactory.createTourDao()) {
            id = tourDao.findByName("NotHotTest").getId();
        }
        tourService.setHot(id);
        boolean initialState = tourService.getById(id).isHot();

        tourService.setNotHot(id);

        assertNotEquals(initialState, tourService.getById(id).isHot());
    }

    @Test
    public void testTourDeletion() {
        Long id;
        createObject("DeletionTest");

        try (TourDao tourDao = daoFactory.createTourDao()) {
            id = tourDao.findByName("DeletionTest").getId();
        }

        int initialSize = tourService.getAll(1, 999).size();
        tourService.deleteById(id);
        tourIds.remove(id);
        assertEquals(initialSize - 1, tourService.getAll(1, 999).size());
    }

    @Test
    public void testCountryFilter() {
        TourFilterDto tourFilterDto = TourFilterDto.newBuilder()
                                                   .setCountry("Japan")
                                                   .build();
        tourFilterDto.changeDefaultValues();

        int countryFilterCtn = tourService.getAllByFilter(tourFilterDto, 1, 999).size();
        createObject("FilterCountryTest");
        assertEquals(countryFilterCtn + 1, tourService.getAllByFilter(tourFilterDto, 1, 999).size());
    }

    @Test
    public void testHotelFilter() {
        TourFilterDto tourFilterDto = TourFilterDto.newBuilder()
                                                   .setHotel("Lodge")
                                                   .build();
        tourFilterDto.changeDefaultValues();

        int hotelFilterCtn = tourService.getAllByFilter(tourFilterDto, 1, 999).size();
        createObject("FilterHotelTest");
        assertEquals(hotelFilterCtn + 1, tourService.getAllByFilter(tourFilterDto, 1, 999).size());
    }

    @Test
    public void testPriceFilter() {
        TourFilterDto tourFilterDto = TourFilterDto.newBuilder()
                                                   .setLowerPrice("19000")
                                                   .setHigherPrice("21000")
                                                   .build();
        tourFilterDto.changeDefaultValues();

        int priceFilterCtn = tourService.getAllByFilter(tourFilterDto, 1, 999).size();
        createObject("FilterPriceTest");
        assertEquals(priceFilterCtn + 1, tourService.getAllByFilter(tourFilterDto, 1, 999).size());
    }

    @Test
    public void testGroupFilter() {
        TourFilterDto tourFilterDto = TourFilterDto.newBuilder()
                                                   .setLowerGroup("14")
                                                   .build();
        tourFilterDto.changeDefaultValues();

        int groupFilterCtn = tourService.getAllByFilter(tourFilterDto, 1, 999).size();
        createObject("FilterGroupTest");
        assertEquals(groupFilterCtn + 1, tourService.getAllByFilter(tourFilterDto, 1, 999).size());
    }

    @Test
    public void testTourWasAdded() {
        int initialSize = tourService.getAll(1, 999).size();
        createObject("Test");
        assertEquals(initialSize + 1, tourService.getAll(1, 999).size());
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
