package com.practice.lld.project_design_practice.dtos;

import com.practice.lld.project_design_practice.models.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class VehiclesResponseDto {

    private List<VehicleDto> vehicles;

    private String errorMessage;

    private ResponseStatus responseStatus;

    public VehiclesResponseDto(final ResponseStatus responseStatus, final List<VehicleDto> vehicles) {
        this.vehicles = vehicles;
    }

    public VehiclesResponseDto(final ResponseStatus responseStatus, final String errorMessage) {
        this.responseStatus = responseStatus;
        this.errorMessage = errorMessage;
    }

    public static VehiclesResponseDto getSuccessfulResponse(final List<Vehicle> vehicles, final Float amount) {

        return new VehiclesResponseDto(ResponseStatus.SUCCESSFUL, mapTovehicleDto(vehicles, amount));
    }

    private static List<VehicleDto> mapTovehicleDto(final List<Vehicle> vehicles, final Float amount) {

        return vehicles.stream().
                map(vehicle -> VehicleDto.builder()
                        .id(vehicle.getId())
                        .model(vehicle.getModel())
                        .type(vehicle.getType())
                        .number(vehicle.getNumber())
                        .amount(amount)
                        .build())
                .collect(Collectors.toList());
    }

    public static VehiclesResponseDto getFailureResponse(final String errorMessage) {

        return new VehiclesResponseDto(ResponseStatus.FAILED, errorMessage);
    }
}
