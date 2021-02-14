package travelagency.dao.impl;

import travelagency.dao.TourDao;
import travelagency.dao.UserDao;
import travelagency.dao.DaoFactory;

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
}
