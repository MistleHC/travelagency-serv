package travelagency.dao.impl;

import travelagency.constants.SQLConstants;
import travelagency.dao.TourDao;
import travelagency.dao.mapper.TourMapper;
import travelagency.model.Tour;
import travelagency.model.TourType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TourDaoImpl implements TourDao {
    private Connection connection;

    private final static Logger logger = Logger.getLogger(TourDaoImpl.class.getName());

    public TourDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Tour findById(Long id) {
        Tour tour = Tour.newBuilder().build();
        logger.info("Receiving tour with id " + id);
        try (PreparedStatement st = connection.prepareStatement(SQLConstants.GET_TOUR_BY_ID)) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            TourMapper tourMapper = new TourMapper();

            while (rs.next()) {
                tour = tourMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            logger.info("ERROR: Couldn't receive tour " + e.getMessage());
        }

        return tour;
    }

    @Override
    public Tour findByName(String name) {
        Tour tour = Tour.newBuilder().build();
        logger.info("Receiving tour with title " + name);

        try (PreparedStatement st = connection.prepareStatement(SQLConstants.GET_TOUR_BY_NAME)) {
            st.setString(1, name);
            ResultSet rs = st.executeQuery();

            TourMapper tourMapper = new TourMapper();

            while (rs.next()) {
                tour = tourMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            logger.info("ERROR: Couldn't receive tour " + e.getMessage());
        }

        return tour;
    }

    @Override
    public List<Tour> findAllByCountry(String country) {
        List<Tour> tours = new ArrayList<>();
        logger.info("Receiving tours for " + country);

        try (PreparedStatement st = connection.prepareStatement(SQLConstants.GET_TOURS_BY_COUNTRY)) {
            st.setString(1, country);
            ResultSet rs = st.executeQuery();

            TourMapper tourMapper = new TourMapper();

            while (rs.next()) {
                Tour tour = tourMapper.extractFromResultSet(rs);
                tours.add(tour);
            }
        } catch (SQLException e) {
            logger.info("ERROR: Couldn't receive tour list " + e.toString());
        }

        return tours;
    }

    @Override
    public List<Tour> findAllByHotelTypeName(String hotel) {
        List<Tour> tours = new ArrayList<>();
        logger.info("Receiving tours for " + hotel + " hotel");

        try (PreparedStatement st = connection.prepareStatement(SQLConstants.GET_TOURS_BY_HOTEL_NAME)) {
            st.setString(1, hotel);
            ResultSet rs = st.executeQuery();

            TourMapper tourMapper = new TourMapper();

            while (rs.next()) {
                Tour tour = tourMapper.extractFromResultSet(rs);
                tours.add(tour);
            }
        } catch (SQLException e) {
            logger.info("ERROR: Couldn't receive tour list " + e.getMessage());
        }

        return tours;
    }

    @Override
    public List<Tour> findAllByCountryAndHotelTypeName(String country, String hotel) {
        List<Tour> tours = new ArrayList<>();
        logger.info("Receiving tours for " + country + " and " + hotel);

        try (PreparedStatement st = connection.prepareStatement(SQLConstants.GET_TOURS_BY_COUNTRY_AND_HOTEL)) {
            st.setString(1, country);
            st.setString(2, hotel);
            ResultSet rs = st.executeQuery();

            TourMapper tourMapper = new TourMapper();

            while (rs.next()) {
                Tour tour = tourMapper.extractFromResultSet(rs);
                tours.add(tour);
            }
        } catch (SQLException e) {
            logger.info("ERROR: Couldn't receive tour list " + e.getMessage());
        }

        return tours;
    }

    @Override
    public List<TourType> getTourTypes() {
        List<TourType> tourTypes = new ArrayList<>();
        logger.info("Receiving tour types");

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SQLConstants.GET_ALL_TOUR_TYPES);

            while (rs.next()) {
                TourType tourType = new TourType(rs.getLong("id"), rs.getString("name"));
                tourTypes.add(tourType);
            }
        } catch (SQLException e) {
            logger.info("ERROR: Couldn't receive tour types list " + e.getMessage());
        }

        return tourTypes;
    }

    @Override
    public TourType getTourTypeByName(String name) {
        TourType tourType = new TourType((long) -1, "");
        logger.info("Receiving tour type with name " + name);

        try (PreparedStatement st = connection.prepareStatement(SQLConstants.GET_TOUR_TYPE_BY_NAME)) {
            st.setString(1, name);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                tourType = new TourType(rs.getLong("id"), rs.getString("name"));
            }

            return tourType;
        } catch (SQLException e) {
            logger.info("ERROR: Couldn't receive tour type using name " + e.getMessage());
            return tourType;
        }
    }

    @Override
    public boolean setHot(Long id, Boolean condition) {
        logger.info("Setting tour with id " + id + " as " + condition);
        try (Statement st = connection.createStatement()){
            int affectedRows = st.executeUpdate(String.format(SQLConstants.UPDATE_TOUR_HOTNESS_VALUE, condition, id));

            if (affectedRows == 0) {
                throw new SQLException("Updating tour 'is_hot' field failed, no rows affected.");
            }

            return true;
        } catch (SQLException e) {
            logger.info("ERROR: Couldn't update tour " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean create(Tour tour) {
        logger.info("Creating tour " + tour.toString());
        try (PreparedStatement st = connection.prepareStatement(SQLConstants.INSERT_NEW_TOUR)) {
            st.setString(1, tour.getName());
            st.setString(2, tour.getDescription());
            st.setString(3, tour.getCountry());
            st.setLong(4, tour.getPeoples());
            st.setLong(5, tour.getHotelType().getId());
            st.setLong(6, tour.getTourType().getId());
            st.setLong(7, tour.getPrice());
            st.setBoolean(8, false);

            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating tour failed, no rows affected.");
            }

            return true;
        } catch (SQLException e) {
            logger.info("ERROR: Couldn't insert new tour " + e.getMessage());
            return false;
        }
    }

    public List<Tour> findAll() {
        List<Tour> tours = new ArrayList<>();
        logger.info("Receiving list of all tours");

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SQLConstants.GET_ALL_TOURS);

            TourMapper tourMapper = new TourMapper();

            while (rs.next()) {
                Tour tour = tourMapper.extractFromResultSet(rs);
                tours.add(tour);
            }
        } catch (SQLException e) {
            logger.info("ERROR: Couldn't receive tour list " + e.getMessage());
        }

        return tours;
    }

    @Override
    public boolean update(Tour entity) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        logger.info("Deleting tour with id " + id);
        try (Statement st = connection.createStatement()){
            int affectedRows = st.executeUpdate(String.format(SQLConstants.DELETE_TOUR_BY_ID, id));

            if (affectedRows == 0) {
                throw new SQLException("Deleting tour failed, no rows affected.");
            }

            return true;
        } catch (SQLException e) {
            logger.info("ERROR: Couldn't delete tour " + e.getMessage());
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
}
