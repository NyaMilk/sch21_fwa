package edu.school21.cinema.repositories;

import edu.school21.cinema.models.AuthData;
import edu.school21.cinema.models.User;

import java.util.List;

public interface LoginRepository {
    User findByUsername(String firstname);

    Long save(User user);

    void saveAuthData(Long id, String ip);

    List<AuthData> getAuthData(Long id);
}
