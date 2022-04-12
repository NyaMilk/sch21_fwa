package edu.school21.cinema.repositories;

import edu.school21.cinema.models.AuthData;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.mapper.AuthDataMapper;
import edu.school21.cinema.repositories.mapper.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

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
    public Long save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO users (firstname, lastname, password, phone) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstname());
            ps.setString(2, user.getLastname());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            return ps;
        }, keyHolder);

        return (Long) Objects.requireNonNull(keyHolder.getKeys()).get("id");
    }

    @Override
    public void saveAuthData(Long id, String ip) {
        jdbc.update("INSERT INTO authentications (idUser, ip) VALUES (?, ?)", id, ip);
    }

    @Override
    public List<AuthData> getAuthData(Long id) {
        return jdbc.query("SELECT * FROM authentications WHERE idUser = ?", new AuthDataMapper(), id);
    }
}
