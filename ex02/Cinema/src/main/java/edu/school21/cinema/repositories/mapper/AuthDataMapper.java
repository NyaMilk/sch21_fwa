package edu.school21.cinema.repositories.mapper;

import edu.school21.cinema.models.AuthData;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class AuthDataMapper implements RowMapper<AuthData> {
    @SneakyThrows
    @Override
    public AuthData mapRow(ResultSet resultSet, int rowNum) {
        return new AuthData(
                resultSet.getTimestamp("date"),
                resultSet.getString("ip")
        );
    }
}
