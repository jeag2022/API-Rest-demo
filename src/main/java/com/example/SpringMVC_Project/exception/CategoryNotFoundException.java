package com.example.SpringMVC_Project.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) { super(message); }
}
