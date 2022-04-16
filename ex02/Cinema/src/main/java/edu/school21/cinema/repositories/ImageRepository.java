package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Photo;

import java.util.List;

public interface ImageRepository {
    Long save(Photo photo);

    List<Photo> findPhotosById(Long id);
}
