package travelagency.dao.impl;

import travelagency.constants.SQLConstants;
import travelagency.dao.TourDao;
import travelagency.dao.mapper.TourMapper;
import travelagency.model.Tour;
import travelagency.model.TourType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TourDaoImpl implements TourDao {
    private Connection connection;

    public TourDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Tour findById(Long id) {
        Tour tour = Tour.newBuilder().build();

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(String.format(SQLConstants.GET_TOUR_BY_ID, id));

            TourMapper tourMapper = new TourMapper();

            while (rs.next()) {
                tour = tourMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Couldn't receive tour list " + e.getMessage());
        }

        return tour;
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
    public List<TourType> getTourTypes() {
        List<TourType> tourTypes = new ArrayList<>();

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SQLConstants.GET_ALL_TOUR_TYPES);

            while (rs.next()) {
                TourType tourType = new TourType(rs.getLong("id"), rs.getString("name"));
                tourTypes.add(tourType);
            }
        } catch (SQLException e) {
            System.err.println("Couldn't receive tour types list " + e.getMessage());
        }

        return tourTypes;
    }

    @Override
    public TourType getTourTypeByName(String name) {
        TourType tourType = new TourType((long) -1, "");

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(String.format(SQLConstants.GET_TOUR_TYPE_BY_NAME, name));

            while (rs.next()) {
                tourType = new TourType(rs.getLong("id"), rs.getString("name"));
            }

            return tourType;
        } catch (SQLException e) {
            System.err.println("Couldn't receive tour type using name " + e.getMessage());
            return tourType;
        }
    }

    @Override
    public boolean setHot(Long id, Boolean condition) {
        try (Statement st = connection.createStatement()){
            int affectedRows = st.executeUpdate(String.format(SQLConstants.UPDATE_TOUR_HOTNESS_VALUE, condition, id));

            if (affectedRows == 0) {
                throw new SQLException("Updating tour 'is_hot' field failed, no rows affected.");
            }

            return true;
        } catch (SQLException e) {
            System.err.println("Couldn't update tour " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean create(Tour tour) {
        try (Statement st = connection.createStatement()) {
            int affectedRows = st.executeUpdate(String.format(SQLConstants.INSERT_NEW_TOUR,
                                                                        tour.getName(),
                                                                        tour.getDescription(),
                                                                        tour.getCountry(),
                                                                        tour.getPeoples(),
                                                                        tour.getHotelType().getId(),
                                                                        tour.getTourType().getId(),
                                                                        tour.getPrice(),
                                                                        false));

            if (affectedRows == 0) {
                throw new SQLException("Creating tour failed, no rows affected.");
            }

            return true;
        } catch (SQLException e) {
            System.err.println("Couldn't insert new tour " + e.getMessage());
            return false;
        }
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
    public boolean delete(Long id) {
        try (Statement st = connection.createStatement()){
            int affectedRows = st.executeUpdate(String.format(SQLConstants.DELETE_TOUR_BY_ID, id));

            if (affectedRows == 0) {
                throw new SQLException("Deleting tour failed, no rows affected.");
            }

            return true;
        } catch (SQLException e) {
            System.err.println("Couldn't delete tour " + e.getMessage());
            return false;
        }
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