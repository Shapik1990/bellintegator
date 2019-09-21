package ru.bellintegrator.practice.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.bellintegrator.practice.view.ErrorResponseView;

@RestControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ex.getBindingResult().getAllErrors().size(); i++) {
            sb.append(" " + ex.getBindingResult().getAllErrors().get(i).getDefaultMessage());
            sb.append(" ");
            sb.append(ex.getBindingResult().getFieldErrors().get(i).getField() + ";");

        }

        return new ResponseEntity<>(new ErrorResponseView("Ошибка валидации :" + sb.toString()),HttpStatus.BAD_REQUEST);
    }

}
