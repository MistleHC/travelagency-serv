package travelagency.service.impl;

import travelagency.dao.UserDao;
import travelagency.dao.DaoFactory;
import travelagency.model.User;
import travelagency.service.UserService;

public class UserServiceImpl implements UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public boolean registerUser(User userToRegister) {
        boolean success;

        try (UserDao dao = daoFactory.createUserDao()) {
            success = dao.create(userToRegister);
        }

        return success;
    }

    @Override
    public User findUserByEmail(String email) {
        User user;

        try (UserDao dao = daoFactory.createUserDao()) {
            user =  dao.findByEmail(email);
        }

        return user;
    }
}
