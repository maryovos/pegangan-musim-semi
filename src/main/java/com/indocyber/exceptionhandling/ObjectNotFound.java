package com.indocyber.exceptionhandling;

public class ObjectNotFound extends RuntimeException{

    public ObjectNotFound() {
        super();
    }

    public ObjectNotFound(String message) {
        super(message);
    }


}