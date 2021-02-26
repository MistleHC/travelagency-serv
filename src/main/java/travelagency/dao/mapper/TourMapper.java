package travelagency.dao.mapper;

import travelagency.model.HotelType;
import travelagency.model.Tour;
import travelagency.model.TourType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TourMapper {
    public Tour extractFromResultSet(ResultSet rs) throws SQLException {
        return Tour.newBuilder()
                .setId(rs.getLong("id"))
                .setName(rs.getString("name"))
                .setDescription(rs.getString("description"))
                .setCountry(rs.getString("country"))
                .setPeoples(rs.getLong("peoples"))
                .setHotelType(new HotelType((long) -1, rs.getString("hotel_type")))
                .setTourType(new TourType((long) -1, rs.getString("tour_type")))
                .setPrice(rs.getLong("price"))
                .setHot(rs.getBoolean("is_hot"))
                .setDiscount(rs.getLong("discount"))
                .build();
    }
}
