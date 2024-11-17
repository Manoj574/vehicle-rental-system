package com.practice.lld.project_design_practice.exception;

public class VehicleNotFoundException  extends RuntimeException {

    public VehicleNotFoundException(final String message) {
        super(message);
    }
}
