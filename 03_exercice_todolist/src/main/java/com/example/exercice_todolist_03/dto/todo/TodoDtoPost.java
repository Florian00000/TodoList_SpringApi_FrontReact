package com.example.exercice_todolist_03.dto.todo;

import com.example.exercice_todolist_03.entity.Todo;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class TodoDtoPost {

    private String title;
    private String description;

    private String dueDate;
    private int idOwner;

    public Todo convertToTodo() {
        return Todo.builder()
                .title(title)
                .description(description)
                .completed(false)
                .dueDate(LocalDate.parse(dueDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();
    }
}
