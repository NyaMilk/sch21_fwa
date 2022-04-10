package edu.school21.cinema.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidException extends RuntimeException {
    String pathURI;

    public ValidException(String message, String path) {
        super(message);
        this.pathURI = path;
    }
}
