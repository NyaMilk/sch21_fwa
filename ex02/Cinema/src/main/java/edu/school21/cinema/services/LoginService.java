package edu.school21.cinema.services;

import edu.school21.cinema.models.AuthData;
import edu.school21.cinema.models.User;

import java.util.List;

public interface LoginService {
    boolean signUp(User user);

    User findUser(String firstname, String password);

    void saveAuth(Long idUser, String ip);

    List<AuthData> getAuth(Long idUser);
}
