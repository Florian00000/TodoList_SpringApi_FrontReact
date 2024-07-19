package com.example.exercice_todolist_03.repository;

import com.example.exercice_todolist_03.entity.Todo;
import com.example.exercice_todolist_03.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Integer> {

    List<Todo> findAllByOwner(User owner);
}
