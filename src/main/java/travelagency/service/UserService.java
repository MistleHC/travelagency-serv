package travelagency.service;

import travelagency.model.User;


public interface UserService {
    boolean registerUser(User userToRegister);
    User findUserByEmail(String email);
}
