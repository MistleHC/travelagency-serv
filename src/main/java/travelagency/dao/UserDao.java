package travelagency.dao;

import travelagency.model.User;

public interface UserDao extends GenericDao<User> {
    User findByEmail(String email);
}
