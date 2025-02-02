package com.practice.student.service.exception;

public class NoSuchStudentException extends RuntimeException {

    public NoSuchStudentException(String message) {
        super(message);
    }
}
