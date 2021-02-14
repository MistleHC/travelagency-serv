package travelagency.dao.impl;

import travelagency.constants.SQLConstants;
import travelagency.dao.TourDao;
import travelagency.dao.mapper.TourMapper;
import travelagency.model.Tour;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TourDaoImpl implements TourDao {
    private Connection connection;

    public TourDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Tour> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Tour> findAllByCountry(String country) {
        List<Tour> tours = new ArrayList<>();

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(String.format(SQLConstants.GET_TOURS_BY_COUNTRY, country));

            TourMapper tourMapper = new TourMapper();

            while (rs.next()) {
                Tour tour = tourMapper.extractFromResultSet(rs);
                tours.add(tour);
            }
        } catch (SQLException e) {
            System.err.println("Couldn't receive tour list " + e.getMessage());
        }

        return tours;
    }

    @Override
    public List<Tour> findAllByHotelType_Name(String hotel) {
        List<Tour> tours = new ArrayList<>();

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(String.format(SQLConstants.GET_TOURS_BY_HOTEL_NAME, hotel));

            TourMapper tourMapper = new TourMapper();

            while (rs.next()) {
                Tour tour = tourMapper.extractFromResultSet(rs);
                tours.add(tour);
            }
        } catch (SQLException e) {
            System.err.println("Couldn't receive tour list " + e.getMessage());
        }

        return tours;
    }

    @Override
    public List<Tour> findAllByCountryAndHotelType_Name(String country, String hotel) {
        List<Tour> tours = new ArrayList<>();

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(String.format(SQLConstants.GET_TOURS_BY_COUNTRY_AND_HOTEL, country, hotel));

            TourMapper tourMapper = new TourMapper();

            while (rs.next()) {
                Tour tour = tourMapper.extractFromResultSet(rs);
                tours.add(tour);
            }
        } catch (SQLException e) {
            System.err.println("Couldn't receive tour list " + e.getMessage());
        }

        return tours;
    }

    @Override
    public boolean setHot(Long id, Boolean condition) {
        return false;
    }

    @Override
    public boolean create(Tour entity) {
        return false;
    }

    @Override
    public Tour findById(int id) {
        return null;
    }

    @Override
    public List<Tour> findAll() {
        List<Tour> tours = new ArrayList<>();

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SQLConstants.GET_ALL_TOURS);

            TourMapper tourMapper = new TourMapper();

            while (rs.next()) {
                Tour tour = tourMapper.extractFromResultSet(rs);
                tours.add(tour);
            }
        } catch (SQLException e) {
            System.err.println("Couldn't receive tour list " + e.getMessage());
        }

        return tours;
    }

    @Override
    public boolean update(Tour entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void doRollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.err.println("Rollback error occurred");
        }
    }
}
