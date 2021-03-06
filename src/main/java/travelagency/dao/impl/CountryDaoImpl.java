package travelagency.dao.impl;

import travelagency.constants.SQLConstants;
import travelagency.dao.CountryDao;
import travelagency.model.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class CountryDaoImpl implements CountryDao {
    private Connection connection;
    private final static Logger logger = Logger.getLogger(CountryDaoImpl.class.getName());


    public CountryDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Country> findAll() {
        List<Country> countries = new ArrayList<>();
        logger.info("Receiving list of countries");

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SQLConstants.GET_ALL_COUNTRIES);

            while (rs.next()) {
                Country country = new Country(rs.getLong("id"), rs.getString("name"));
                countries.add(country);
            }
        } catch (SQLException e) {
            logger.info("ERROR: Couldn't receive countries list " + e.getMessage());
        }

        return countries;
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
