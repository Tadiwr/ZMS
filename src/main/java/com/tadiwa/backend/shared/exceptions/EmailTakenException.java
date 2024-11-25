package com.tadiwa.backend.shared.exceptions;

public class EmailTakenException extends Exception {
    
    @Override
    public String getMessage() {
        return "Email already taken by another user";
    }
}
