package com.practice.lld.project_design_practice.controllers;

import com.practice.lld.project_design_practice.dtos.StoresResponseDto;
import com.practice.lld.project_design_practice.dtos.VehiclesResponseDto;
import com.practice.lld.project_design_practice.models.Store;
import com.practice.lld.project_design_practice.models.Vehicle;
import com.practice.lld.project_design_practice.services.StoresService;
import com.practice.lld.project_design_practice.strategy.IPriceCalculationStrategy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoresController {

    private final StoresService storesService;

    private final IPriceCalculationStrategy priceCalculationStrategy;

    public StoresController(final StoresService storesService, final IPriceCalculationStrategy priceCalculationStrategy) {
        this.storesService = storesService;
        this.priceCalculationStrategy = priceCalculationStrategy;
    }

    @GetMapping
    public ResponseEntity<StoresResponseDto> fetchStores(@RequestParam("location") String location) {
        final List<Store> stores;
        try {
             stores = storesService.getStores(location);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(StoresResponseDto.getFailureResponse("Unable to service the request"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (stores.isEmpty()) {
            return new ResponseEntity<>(StoresResponseDto.getFailureResponse("Our app don't have service in requested location"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(StoresResponseDto.getSuccessfulResponse(stores), HttpStatus.OK);
    }

    @GetMapping("/{store_id}/vehicles")
    public ResponseEntity<VehiclesResponseDto> fetchVehicles(@PathVariable("store_id") Long storeId,
                                                             @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
                                                             @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {

        final List<Vehicle> vehicles;

        try {
            vehicles = storesService.getVehicles(storeId, fromDate, toDate);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(VehiclesResponseDto.getFailureResponse("Unable to service the Request"), HttpStatus.INTERNAL_SERVER_ERROR);
        }


        if (vehicles.isEmpty()) {
            return new ResponseEntity<>(VehiclesResponseDto.getFailureResponse("No vehicles available"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(VehiclesResponseDto.getSuccessfulResponse(vehicles, priceCalculationStrategy.getPrice(fromDate, toDate)), HttpStatus.OK);
    }
}
