package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Photo;
import edu.school21.cinema.repositories.mapper.PhotoMapper;
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
public class ImageRepositoryImpl implements ImageRepository {
    private final JdbcTemplate jdbc;

    public ImageRepositoryImpl(DriverManagerDataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public Long save(Photo photo) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO photos (iduser, name, path, size, mime) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, photo.getIdUser());
            ps.setString(2, photo.getName());
            ps.setString(3, photo.getPath());
            ps.setLong(4, photo.getSize());
            ps.setString(5, photo.getMime());
            return ps;
        }, keyHolder);

        return (Long) Objects.requireNonNull(keyHolder.getKeys()).get("id");
    }

    @Override
    public List<Photo> findPhotosById(Long id) {
        return jdbc.query("SELECT * FROM photos WHERE idUser = ? ORDER BY ID DESC", new PhotoMapper(), id);
    }
}
