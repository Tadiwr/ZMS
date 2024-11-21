package com.tadiwa.backend.shared.exceptions;

public class IdNumberAlreadyTaken extends Exception {
    
    @Override
    public String getMessage() {
        return "A user with the same ID already exists";
    }
}
