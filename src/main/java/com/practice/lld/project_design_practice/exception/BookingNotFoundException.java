package com.practice.lld.project_design_practice.exception;

public class BookingNotFoundException extends RuntimeException {

    public BookingNotFoundException(final String message) {
        super(message);
    }
}
