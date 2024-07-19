package com.example.exercice_todolist_03.controller;

import com.example.exercice_todolist_03.dto.todo.TodoDtoGet;
import com.example.exercice_todolist_03.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<List<TodoDtoGet>> getAllTodos() {
        return ResponseEntity.ok(todoService.findAll());
    }

}
