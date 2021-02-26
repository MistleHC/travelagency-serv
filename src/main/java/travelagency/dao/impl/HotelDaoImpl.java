package travelagency.dao.impl;

import travelagency.constants.SQLConstants;
import travelagency.dao.HotelDao;
import travelagency.model.HotelType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class HotelDaoImpl implements HotelDao {
    private Connection connection;

    private final static Logger logger = Logger.getLogger(HotelDaoImpl.class.getName());

    public HotelDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<HotelType> findAll() {
        List<HotelType> hotels = new ArrayList<>();
        logger.info("Receiving all hotel types");

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SQLConstants.GET_ALL_HOTELS);

            while (rs.next()) {
                HotelType hotel = new HotelType(rs.getLong("id"), rs.getString("name"));
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            logger.info("ERROR: Couldn't receive hotels list " + e.getMessage());
        }

        return hotels;
    }

    @Override
    public HotelType getByName(String name) {
        HotelType hotel = new HotelType((long) -1, "");
        logger.info("Receiving hotel type with name " + name);

        try (PreparedStatement st = connection.prepareStatement(SQLConstants.GET_HOTEL_BY_NAME)) {
            st.setString(1, name);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                hotel = new HotelType(rs.getLong("id"), rs.getString("name"));
            }

            return hotel;
        } catch (SQLException e) {
            logger.info("ERROR: Couldn't receive hotel using name " + e.getMessage());
            return hotel;
        }
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
