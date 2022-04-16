package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;

public interface LoginRepository {
    User findByUsername(String firstname);

    void save(User user);
}
