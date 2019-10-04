package ru.bellintegrator.practice.view;


import com.fasterxml.jackson.annotation.JsonView;
import ru.bellintegrator.practice.validation.ShowDto;

@JsonView(ShowDto.class)
public class DataResponseView <T>{

    private T data;

    public DataResponseView() {
    }

    public DataResponseView(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
