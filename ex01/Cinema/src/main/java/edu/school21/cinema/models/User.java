package edu.school21.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String firstname;
    private String lastname;
    private String password;
    private String phone;

    public User(String firstname, String lastname, String password, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.phone = phone;
    }
}
