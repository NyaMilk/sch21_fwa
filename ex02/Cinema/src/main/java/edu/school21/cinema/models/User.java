package edu.school21.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {
    private Long id;
    private String firstname;
    private String lastname;
    private String password;
    private String phone;
    private List<AuthData> authData;

    public User(Long id, String firstname, String lastname, String password, String phone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.phone = phone;
    }

    public User(String firstname, String lastname, String password, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.phone = phone;
    }
}
