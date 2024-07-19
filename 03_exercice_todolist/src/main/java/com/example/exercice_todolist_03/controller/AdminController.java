package com.example.exercice_todolist_03.controller;

import com.example.exercice_todolist_03.dto.todo.TodoDtoGet;
import com.example.exercice_todolist_03.dto.todo.TodoDtoPost;
import com.example.exercice_todolist_03.entity.Role;
import com.example.exercice_todolist_03.service.TodoService;
import com.example.exercice_todolist_03.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private TodoService todoService;

    @PostMapping("/create_Role")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createRole(role));
    }

    @DeleteMapping("/delete_role")
    public ResponseEntity<String> deleteRole(@RequestBody Role role) {
        userService.deleteRole(role.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/todo/add")
    public ResponseEntity<TodoDtoGet> addTodo(@RequestBody TodoDtoPost todoDtoPost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(todoDtoPost));
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<List<TodoDtoGet>> getTodosByOwner(@PathVariable int id) {
        return ResponseEntity.ok(todoService.findAllByOwnerId(id));
    }

    @PutMapping("/todo/{id}")
    public ResponseEntity<TodoDtoGet> updateTodo(@PathVariable int id, @RequestBody TodoDtoPost todoDtoPost) {
        return ResponseEntity.ok(todoService.update(id,todoDtoPost));
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable int id) {
        todoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
