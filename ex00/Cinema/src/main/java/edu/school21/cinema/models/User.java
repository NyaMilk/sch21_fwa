package edu.school21.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private Long id;
    private String firstname;
    private String lastname;
    private String password;
    private String phone;
}
