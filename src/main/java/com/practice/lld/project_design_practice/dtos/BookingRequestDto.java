package com.practice.lld.project_design_practice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class BookingRequestDto {

    @NotNull
    private Long vehicleId;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fromDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toDate;

    @NotNull
    private Float amount;
}
