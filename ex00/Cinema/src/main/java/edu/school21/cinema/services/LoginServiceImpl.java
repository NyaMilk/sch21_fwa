package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.LoginRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LoginServiceImpl implements LoginService {
    private final LoginRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginServiceImpl(LoginRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.getUserById(id);
    }
}
