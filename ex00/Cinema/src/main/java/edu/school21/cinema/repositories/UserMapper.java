package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new User(
                resultSet.getString("firstname"),
                resultSet.getString("lastname"),
                resultSet.getString("password"),
                resultSet.getString("phone")
        );
    }
}
