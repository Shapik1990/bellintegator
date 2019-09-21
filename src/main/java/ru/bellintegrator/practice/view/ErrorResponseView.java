package ru.bellintegrator.practice.view;

import com.fasterxml.jackson.annotation.JsonView;
import ru.bellintegrator.practice.transfer.ShowDto;

@JsonView(ShowDto.class)
public class ErrorResponseView {

    private String error;

    public ErrorResponseView(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
