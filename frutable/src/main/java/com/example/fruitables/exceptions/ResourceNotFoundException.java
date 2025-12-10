package com.example.fruitables.exceptions;

public class ResourceNotFoundException  extends RuntimeException{
    private String message;
    private String resource;
    private Long id;

    public ResourceNotFoundException(String resource,  Long id) {
        super(String.format("%s with id %d not found", resource, id));

    }
}
