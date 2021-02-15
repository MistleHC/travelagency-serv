package travelagency.dao.impl;

import travelagency.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }

    @Override
    public TourDao createTourDao() {
        return new TourDaoImpl(getConnection());
    }

    @Override
    public CountryDao createCountryDao() { return new CountryDaoImpl(getConnection()); }

    @Override
    public HotelDao createHotelDao() { return new HotelDaoImpl(getConnection()); }

    @Override
    public OrderDao createOrderDao() { return new OrderDaoImpl(getConnection()); }
}
