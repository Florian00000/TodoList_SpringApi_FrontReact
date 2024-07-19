package com.example.exercice_todolist_03.dto.todo;

import com.example.exercice_todolist_03.entity.Todo;
import com.example.exercice_todolist_03.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodoDtoGet {

    private int id;

    private String title;
    private String description;

    private boolean completed;
    private LocalDate dueDate;

    private User owner;

    public TodoDtoGet(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.description = todo.getDescription();
        this.completed = todo.isCompleted();
        this.dueDate = todo.getDueDate();
        this.owner = todo.getOwner();
    }
}
