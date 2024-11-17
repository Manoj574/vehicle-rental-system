package com.practice.lld.project_design_practice.services;

import com.practice.lld.project_design_practice.dtos.BookingRequestDto;
import com.practice.lld.project_design_practice.exception.BookingNotFoundException;
import com.practice.lld.project_design_practice.exception.VehicleNotAvailableException;
import com.practice.lld.project_design_practice.exception.VehicleNotFoundException;
import com.practice.lld.project_design_practice.models.BookingRequest;
import com.practice.lld.project_design_practice.models.BookingRequestStatus;
import com.practice.lld.project_design_practice.models.Vehicle;
import com.practice.lld.project_design_practice.repository.IBookingRequestRepository;
import com.practice.lld.project_design_practice.strategy.IPriceCalculationStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {

    private final VehicleService vehicleService;

    private final IBookingRequestRepository bookingRequestRepository;

    private final IPriceCalculationStrategy priceCalculationStrategy;

    public BookingService(final VehicleService vehicleService, final IBookingRequestRepository bookingRequestRepository, IPriceCalculationStrategy priceCalculationStrategy) {
        this.vehicleService = vehicleService;
        this.bookingRequestRepository = bookingRequestRepository;
        this.priceCalculationStrategy = priceCalculationStrategy;
    }

    @Transactional
    public BookingRequest bookVehicle(final BookingRequestDto requestDto) {

        final Vehicle vehicle = vehicleService.findVehicleById(requestDto.getVehicleId())
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));
        if (!vehicle.isAvailable(requestDto.getFromDate(), requestDto.getToDate())) {
            throw new VehicleNotAvailableException("Vehicle not available");
        }

        final BookingRequest bookingRequest = BookingRequest.getBookingRequest(requestDto, vehicle);
        return bookingRequestRepository.save(bookingRequest);
    }

    public void completeBooking(final Long bookingId) {
        final BookingRequest bookingRequest = bookingRequestRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));
        bookingRequest.setStatus(BookingRequestStatus.COMPLETED);
        bookingRequestRepository.save(bookingRequest);
    }
}
