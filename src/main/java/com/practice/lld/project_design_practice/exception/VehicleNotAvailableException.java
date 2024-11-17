package com.practice.lld.project_design_practice.exception;

public class VehicleNotAvailableException extends RuntimeException {

    public VehicleNotAvailableException(final String message) {
        super(message);
    }
}
