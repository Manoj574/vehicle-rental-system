package com.practice.lld.project_design_practice.repository;

import com.practice.lld.project_design_practice.dtos.BookingRequestDto;
import com.practice.lld.project_design_practice.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {
}
