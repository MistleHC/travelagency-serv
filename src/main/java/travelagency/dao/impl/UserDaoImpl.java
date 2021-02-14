package travelagency.dao.impl;

import travelagency.Encryption.AESEncryptor;
import travelagency.constants.SQLConstants;
import travelagency.dao.UserDao;
import travelagency.dao.mapper.UserMapper;
import travelagency.model.User;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class UserDaoImpl implements UserDao {
    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;

        try (Statement st = connection.createStatement()) {
            connection.setAutoCommit(false);
            ResultSet rs = st.executeQuery(String.format(SQLConstants.GET_USER_BY_EMAIL, email));
            System.out.println("SQL: " + String.format(SQLConstants.GET_USER_BY_EMAIL, email));
            UserMapper userMapper = new UserMapper();
            while (rs.next()) {
                user = userMapper.extractFromResultSet(rs);
            }
            user = getUserRole(st, user);

            connection.commit();
            return user;
        } catch (SQLException e) {
            System.err.println("Couldn't find user " + e.getMessage());
            doRollback(connection);
        }

        return user;
    }

    @Override
    public boolean create(User user) {
        System.out.println("Adding: " + user.toString());
        try (Statement st = connection.createStatement()) {
            connection.setAutoCommit(false);

            int affectedRows = st.executeUpdate(String.format(SQLConstants.INSERT_USER_WITH_NAME_EMAIL_PW,
                                                                        user.getEmail(),
                                                                        user.getName(),
                                                                        user.getPassword()));

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
            System.err.println("Couldn't add user or role " + e.getMessage());
            doRollback(connection);
            return false;
        }
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
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
        System.out.println("Adding users role");
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(String.format(SQLConstants.INSERT_ROLE_FOR_USER, user.getId(), roleId));
            return true;
        }
    }

    private User getUserRole(Statement st, User user) throws SQLException {
        if (Objects.nonNull(user)) {
            ResultSet rsTwo = st.executeQuery(String.format(SQLConstants.GET_USER_ROLE_BY_USER_ID, user.getId()));
            System.out.println("SQL: " + String.format(SQLConstants.GET_USER_ROLE_BY_USER_ID, user.getId()));
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
            System.err.println("Rollback error occurred");
        }
    }
}
