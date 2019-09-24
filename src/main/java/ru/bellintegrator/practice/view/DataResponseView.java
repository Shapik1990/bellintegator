package ru.bellintegrator.practice.view;


import com.fasterxml.jackson.annotation.JsonView;
import ru.bellintegrator.practice.validation.ShowDto;

@JsonView(ShowDto.class)
public class DataResponseView {

    private Object data;

    public DataResponseView(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
