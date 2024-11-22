package com.tadiwa.backend.shared.exceptions;


public class NotFound extends Exception {


    @Override
    public String getMessage() {
        return "Resourse Not Found";
    }
}
