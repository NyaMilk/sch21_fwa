package edu.school21.cinema.repositories.mapper;

import edu.school21.cinema.models.User;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class UserMapper implements RowMapper<User> {
    @SneakyThrows
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("firstname"),
                resultSet.getString("lastname"),
                resultSet.getString("password"),
                resultSet.getString("phone")
        );
    }
}
