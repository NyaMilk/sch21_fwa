package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;

public interface LoginRepository {
    void save(User user);

    User getUserById(Long id);
}
