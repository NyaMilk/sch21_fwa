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
    public void save(User user) {
    }

    @Override
    public User getUserById(Long id) {
        return jdbc.query("SELECT * FROM Users WHERE id = ?", new UserMapper(), id)
                .stream().findAny().orElse(null);
    }
}
