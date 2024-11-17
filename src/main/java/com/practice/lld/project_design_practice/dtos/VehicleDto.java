package com.practice.lld.project_design_practice.dtos;

import com.practice.lld.project_design_practice.models.VehicleType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VehicleDto {

    private Long id;

    private String number;

    private VehicleType type;

    private String model;

    private Float amount;
}
