package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepositoryImpl implements LoginRepository {
    private final JdbcTemplate jdbc;

    public LoginRepositoryImpl(DriverManagerDataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public User findByUsername(String firstname) {
        return jdbc.query("SELECT * FROM users WHERE firstname = ?", new UserMapper(), firstname)
                .stream().findFirst().orElse(null);
    }

    @Override
    public void save(User user) {
        jdbc.update("INSERT INTO users (firstname, lastname, password, phone) VALUES (?, ?, ?, ?)",
                user.getFirstname(), user.getLastname(), user.getPassword(), user.getPhone());
    }
}
