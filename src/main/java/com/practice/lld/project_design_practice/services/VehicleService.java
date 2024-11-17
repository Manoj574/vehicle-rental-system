package com.practice.lld.project_design_practice.services;

import com.practice.lld.project_design_practice.models.Vehicle;
import com.practice.lld.project_design_practice.repository.IVehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService {

    private final IVehicleRepository vehicleRepository;

    public VehicleService(final IVehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Optional<Vehicle> findVehicleById(final Long vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }
}
