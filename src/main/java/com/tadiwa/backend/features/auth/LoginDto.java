package com.tadiwa.backend.features.auth;

public record LoginDto (
    String email,
    String password
) {
    
}
