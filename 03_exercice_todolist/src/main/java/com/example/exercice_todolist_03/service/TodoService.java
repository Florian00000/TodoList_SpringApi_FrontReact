package com.example.exercice_todolist_03.service;

import com.example.exercice_todolist_03.dto.todo.TodoDtoGet;
import com.example.exercice_todolist_03.dto.todo.TodoDtoPost;
import com.example.exercice_todolist_03.entity.Todo;
import com.example.exercice_todolist_03.entity.User;
import com.example.exercice_todolist_03.exception.NotFoundException;
import com.example.exercice_todolist_03.repository.TodoRepository;
import com.example.exercice_todolist_03.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService implements BaseService<TodoDtoGet, TodoDtoPost> {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    public TodoDtoGet createTodo(TodoDtoPost todoDtoPost) {
        if (userRepository.findById(todoDtoPost.getIdOwner()).isPresent()){
            Todo todo = todoDtoPost.convertToTodo();
            todo.setOwner(userRepository.findById(todoDtoPost.getIdOwner()).get());
            todoRepository.save(todo);
            return new TodoDtoGet(todo);
        }else{
            throw new NotFoundException();
        }

    }

    @Override
    public TodoDtoGet update(int id, TodoDtoPost todoDtoPost) {
        Todo todo = todoRepository.findById(id).orElseThrow(NotFoundException::new);
        todo.setTitle(todoDtoPost.getTitle());
        todo.setDescription(todoDtoPost.getDescription());
        todo.setCompleted(todo.isCompleted());
        todoRepository.save(todo);
        return new TodoDtoGet(todo);
    }

    @Override
    public boolean delete(int id) {
        Todo todo = todoRepository.findById(id).orElseThrow(NotFoundException::new);
        todoRepository.delete(todo);
        return true;
    }

    @Override
    public TodoDtoGet findById(int id) {
        return null;
    }

    @Override
    public List<TodoDtoGet> findAll() {
        List <Todo> todos = (List<Todo>) todoRepository.findAll();
        return todos.stream().map(TodoDtoGet::new).collect(Collectors.toList());
    }

    public List<TodoDtoGet> findAllByOwnerId(int idOwner) {
        User user = userRepository.findById(idOwner).orElseThrow(NotFoundException::new);
        List<Todo> todos = todoRepository.findAllByOwner(user);
        return todos.stream().map(TodoDtoGet::new).collect(Collectors.toList());
    }
}
