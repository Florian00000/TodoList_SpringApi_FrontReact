package com.example.exercice_todolist_03.repository;

import com.example.exercice_todolist_03.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Optional<Role> findByGrade(String grade);
}
