package ru.bellintegrator.practice.view;


import com.fasterxml.jackson.annotation.JsonView;
import ru.bellintegrator.practice.transfer.ShowDto;

@JsonView(ShowDto.class)
public class DataResponseView<T> {

    private T data;

    public DataResponseView(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
