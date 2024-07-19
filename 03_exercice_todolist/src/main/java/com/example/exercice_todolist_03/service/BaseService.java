package com.example.exercice_todolist_03.service;

import java.util.List;

public interface BaseService<Tget, Tpost> {


    Tget update (int id, Tpost object);
    boolean delete (int id);
    Tget findById (int id);
    List<Tget> findAll ();
}
