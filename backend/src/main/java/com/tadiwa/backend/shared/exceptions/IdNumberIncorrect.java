package com.tadiwa.backend.shared.exceptions;

public class IdNumberIncorrect extends Exception {

    @Override
    public String getMessage() {
        return "Invalid ID Number";
    }
    
}