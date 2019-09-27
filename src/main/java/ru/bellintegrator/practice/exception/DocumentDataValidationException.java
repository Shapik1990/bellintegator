package ru.bellintegrator.practice.exception;

/**
 * Исключение при некорректном или не полном заполнении данных документа
 */
public class DocumentDataValidationException extends RuntimeException {

    public DocumentDataValidationException(String message) {
        super(message);
    }
}
