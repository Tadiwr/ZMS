package com.tadiwa.backend.shared.exceptions;

public class InvalidJwtToken extends Exception {
    @Override
    public String getMessage() {
        return "Invalid JWT token";
    }
}
