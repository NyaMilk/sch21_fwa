package edu.school21.cinema.services;

import edu.school21.cinema.models.User;

public interface LoginService {
    boolean signUp(User user);

    User findUser(String username, String password);
}
