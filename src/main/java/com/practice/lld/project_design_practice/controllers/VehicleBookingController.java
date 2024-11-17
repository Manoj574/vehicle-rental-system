package com.practice.lld.project_design_practice.controllers;

import com.practice.lld.project_design_practice.dtos.BookingRequestDto;
import com.practice.lld.project_design_practice.models.BookingRequest;
import com.practice.lld.project_design_practice.services.BookingService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicles")
public class VehicleBookingController {

    private final BookingService bookingService;

    public VehicleBookingController(final BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping("/book")
    public ResponseEntity<String> reserveVehicle(@RequestBody @Valid final BookingRequestDto requestDto, final BindingResult bindingResult) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        if (bindingResult.hasErrors()) {
                httpHeaders.set("X-Error", getErrorMessage(bindingResult));
                return ResponseEntity.badRequest().headers(httpHeaders).build();
        }

        final BookingRequest bookingRequest = bookingService.bookVehicle(requestDto);

        httpHeaders.set("X-BookingId", bookingRequest.getId().toString());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/booking/{bookingId}/complete")
    private ResponseEntity<String> getErrorMessage(@PathVariable("bookingId") final Long bookingId) {

        bookingService.completeBooking(bookingId);
        return ResponseEntity.ok("Booking marked as completed");
    }

    private String getErrorMessage(final BindingResult bindingResult) {

        return bindingResult.getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
    }

}
