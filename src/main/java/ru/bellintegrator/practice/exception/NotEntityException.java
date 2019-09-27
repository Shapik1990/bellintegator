package ru.bellintegrator.practice.exception;

/**
 * Исключение при отсутсвии запрашиваемого объекта в БД
 */
public class NotEntityException extends RuntimeException {

    public NotEntityException(String message) {
        super(message);
    }
}
