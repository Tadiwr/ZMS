package com.tadiwa.backend.shared.exceptions;

public class ProvinceNotFound extends Exception {
    @Override
    public String getMessage() {
        return "Province was not found";
    }
}
