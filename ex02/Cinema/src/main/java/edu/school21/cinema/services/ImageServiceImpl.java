package edu.school21.cinema.services;

import edu.school21.cinema.models.Photo;
import edu.school21.cinema.repositories.ImageRepository;

import java.util.List;

public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public long save(Photo photo) {
        return imageRepository.save(photo);
    }

    @Override
    public List<Photo> getPhotos(Long id) {
        return imageRepository.findPhotosById(id);
    }
}
