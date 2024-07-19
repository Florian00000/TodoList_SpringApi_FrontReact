package com.example.exercice_todolist_03.dto;

import lombok.Data;

@Data
public class BaseResponsedto {

    private Object message;
    private Object data;

    public BaseResponsedto(Object message) {
        this.message = message;
    }

    public BaseResponsedto(Object message, Object data) {
        this.message = message;
        this.data = data;
    }
}
