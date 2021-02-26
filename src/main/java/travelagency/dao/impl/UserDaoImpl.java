package travelagency.dao.impl;

import travelagency.Encryption.AESEncryptor;
import travelagency.constants.SQLConstants;
import travelagency.dao.UserDao;
import travelagency.dao.mapper.UserMapper;
import travelagency.model.User;

import java.sql.*;
import java.util.logging.Logger;

public class UserDaoImpl implements UserDao {
    private Connection connection;

    private final static Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;

        try (PreparedStatement st = connection.prepareStatement(SQLConstants.GET_USER_BY_EMAIL)) {
            st.setString(1, email);

            ResultSet rs = st.executeQuery();

            UserMapper userMapper = new UserMapper();
            while (rs.next()) {
                user = userMapper.extractFromResultSet(rs);
            }

            if (user == null) {
                throw new SQLException("User not found");
            }

            user = getUserRole(user);
            logger.info("User with email " + email + " was received");

            return user;
        } catch (SQLException e) {
            logger.info("ERROR: Couldn't find user - " + e.getMessage());
        }

        return user;
    }

    @Override
    public boolean create(User user) {
        logger.info("Adding: " + user.toString());
        try (PreparedStatement st = connection.prepareStatement(SQLConstants.INSERT_USER_WITH_NAME_EMAIL_PW)) {
            connection.setAutoCommit(false);

            st.setString(1, user.getEmail());
            st.setString(2, user.getName());
            st.setString(3, AESEncryptor.encrypt(user.getPassword(), "travel"));

            int affectedRows = st.executeUpdate();

            User insertedUser = findByEmail(user.getEmail());

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            if (setUserRole(insertedUser, 1)) {
                insertedUser.setRole("CUSTOMER");
                connection.commit();
            } else {
                throw new SQLException("Creating users role failed.");
            }

            return true;
        } catch (SQLException e) {
            logger.info("ERROR: Couldn't add user or role - " + e.getMessage());
            doRollback(connection);
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        logger.info("Updating user: " + user.toString());
        try (PreparedStatement st = connection.prepareStatement(SQLConstants.UPDATE_USER_ADDITIONAL_INFO)) {
            st.setString(1, user.getAboutMe());
            st.setString(2, user.getFullName());
            st.setLong(3, user.getId());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating user details failed, no rows affected.");
            }

            return true;
        } catch (SQLException e) {
            logger.info("ERROR: Couldn't update user " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        return false; //TODO: Implement user deletion.
    }

    @Override
    public void close()  {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean setUserRole(User user, int roleId) throws SQLException {
        logger.info("Adding role with id " + roleId);
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(String.format(SQLConstants.INSERT_ROLE_FOR_USER, user.getId(), roleId));
            return true;
        }
    }

    private User getUserRole(User user) throws SQLException {
        logger.info("Receiving user roles, users email - " + user.getEmail());
        try (Statement stm = connection.createStatement()) {
            ResultSet rsTwo = stm.executeQuery(String.format(SQLConstants.GET_USER_ROLE_BY_USER_ID, user.getId()));

            while (rsTwo.next()) {
                user.setRole(rsTwo.getString("name"));
            }
        }

        return user;
    }

    private void doRollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.info("ERROR: Rollback error occurred");
        }
    }
}
