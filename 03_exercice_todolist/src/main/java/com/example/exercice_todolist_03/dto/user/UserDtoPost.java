package com.example.exercice_todolist_03.dto.user;

import com.example.exercice_todolist_03.entity.User;
import lombok.Data;

@Data
public class UserDtoPost {

    private String firstname;
    private String lastname;
    private String email;
    private String password;

    public User convertToUser() {
        return User.builder()
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .password(password)
                .build();
    }
}
