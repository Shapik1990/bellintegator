package ru.bellintegrator.practice.view;


public class ErrorResponseView {

    private String error;

    public ErrorResponseView() {
    }

    public ErrorResponseView(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
