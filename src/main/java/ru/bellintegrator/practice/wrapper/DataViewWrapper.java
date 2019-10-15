package ru.bellintegrator.practice.wrapper;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.bellintegrator.practice.view.DataResponseView;
import ru.bellintegrator.practice.view.ErrorResponseView;
import ru.bellintegrator.practice.view.SuccessResponseView;


@RestControllerAdvice
public class DataViewWrapper implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return methodParameter.getParameterType() != SuccessResponseView.class && methodParameter.getParameterType() != ErrorResponseView.class
                && methodParameter.getParameterType() != ResponseEntity.class;
    }


    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return new DataResponseView<>(o);
    }
}
