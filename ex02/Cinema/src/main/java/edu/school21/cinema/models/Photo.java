package edu.school21.cinema.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Photo {
    private Long id;
    private Long idUser;
    private String name;
    private String path;
    private Long size;
    private String mime;

    public Photo(Long idUser, String name, String path, Long size, String mime) {
        this.idUser = idUser;
        this.name = name;
        this.path = path;
        this.size = size;
        this.mime = mime;
    }
}
