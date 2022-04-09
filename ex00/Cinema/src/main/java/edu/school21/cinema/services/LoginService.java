package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.LoginRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface LoginService {
    User findUserById(Long id);
}
