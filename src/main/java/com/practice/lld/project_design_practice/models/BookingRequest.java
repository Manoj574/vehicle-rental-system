package com.practice.lld.project_design_practice.models;

import com.practice.lld.project_design_practice.dtos.BookingRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "booking_requests")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    @Id
    @Generated
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Vehicle vehicle;

    @Basic
    @Column(name = "from_date")
    private Date fromDate;

    @Basic
    @Column(name = "to_date")
    private Date toDate;

    @Basic
    private Float amount;

    @Basic
    @Enumerated(value = EnumType.STRING)
    private BookingRequestStatus status;

    public static BookingRequest getBookingRequest(final BookingRequestDto requestDto, final Vehicle vehicle) {
        final BookingRequest bookingRequest = BookingRequest.builder()
                .fromDate(requestDto.getFromDate())
                .toDate(requestDto.getToDate())
                .amount(requestDto.getAmount())
                .status(BookingRequestStatus.BOOKED)
                .build();
        bookingRequest.setVehicle(vehicle);
        return bookingRequest;
    }
}
