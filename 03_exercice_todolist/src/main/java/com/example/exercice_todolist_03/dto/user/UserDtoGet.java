package com.example.exercice_todolist_03.dto.user;


import com.example.exercice_todolist_03.entity.Role;
import com.example.exercice_todolist_03.entity.Todo;
import com.example.exercice_todolist_03.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDtoGet {

    private int id;
    private String firstname;
    private String lastname;
    private String email;

    private Role role;
    private List<Todo> todos;

    public UserDtoGet( User user) {
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.todos = user.getTodos();
    }
}
