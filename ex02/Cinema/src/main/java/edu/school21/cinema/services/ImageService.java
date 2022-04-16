package edu.school21.cinema.services;

import edu.school21.cinema.models.Photo;

import java.util.List;

public interface ImageService {
    long save(Photo photo);

    List<Photo> getPhotos(Long id);
}
