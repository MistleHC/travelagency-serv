package travelagency.dao.impl;

import travelagency.constants.SQLConstants;
import travelagency.dao.HotelDao;
import travelagency.model.HotelType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HotelDaoImpl implements HotelDao {
    private Connection connection;

    public HotelDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<HotelType> findAll() {
        List<HotelType> hotels = new ArrayList<>();

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SQLConstants.GET_ALL_HOTELS);

            while (rs.next()) {
                HotelType hotel = new HotelType(rs.getLong("id"), rs.getString("name"));
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            System.err.println("Couldn't receive hotels list " + e.getMessage());
        }

        return hotels;
    }

    @Override
    public HotelType getByName(String name) {
        HotelType hotel = new HotelType((long) -1, "");

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(String.format(SQLConstants.GET_HOTEL_BY_NAME, name));

            while (rs.next()) {
                hotel = new HotelType(rs.getLong("id"), rs.getString("name"));
            }

            return hotel;
        } catch (SQLException e) {
            System.err.println("Couldn't receive hotel using name " + e.getMessage());
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
