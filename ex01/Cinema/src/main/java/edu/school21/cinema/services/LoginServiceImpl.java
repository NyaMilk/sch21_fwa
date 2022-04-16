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
    public boolean signUp(User user) {
        User findUser = userRepository.findByUsername(user.getFirstname());

        if (findUser != null) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public User findUser(String firstname, String password) {
        User user = userRepository.findByUsername(firstname);

        if (user != null) {
            if (!passwordEncoder.matches(password, user.getPassword())) {
                user.setPassword(null);
            }
            return user;
        }

        return null;
    }
}
