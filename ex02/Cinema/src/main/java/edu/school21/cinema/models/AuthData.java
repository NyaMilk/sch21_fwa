package edu.school21.cinema.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class AuthData {
//    private Long id;
    private Timestamp timestamp;
    private String ip;

    public AuthData(Timestamp timestamp, String ip) {
        this.timestamp = timestamp;
        this.ip = ip;
    }
}
