package edu.school21.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class AuthData {
    private Timestamp timestamp;
    private String ip;
}
