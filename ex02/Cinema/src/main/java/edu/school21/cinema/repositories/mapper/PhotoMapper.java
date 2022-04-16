package edu.school21.cinema.repositories.mapper;

import edu.school21.cinema.models.Photo;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class PhotoMapper implements RowMapper<Photo> {
    @SneakyThrows
    @Override
    public Photo mapRow(ResultSet resultSet, int rowNum) {
        return new Photo(
                resultSet.getLong("id"),
                resultSet.getLong("idUser"),
                resultSet.getString("name"),
                resultSet.getString("path"),
                resultSet.getLong("size"),
                resultSet.getString("mime")
        );
    }
}
