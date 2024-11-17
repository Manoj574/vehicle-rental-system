package com.practice.lld.project_design_practice.models;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
public class Vehicle {

    @Id
    @Generated
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Basic
    private String number;

    @Basic
    @Enumerated(value = EnumType.STRING)
    private VehicleType type;

    @Basic
    private String model;

    @ManyToOne(cascade = CascadeType.ALL)
    private Store store;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<BookingRequest> bookingRequests;

    public boolean isAvailable(final Date fromDate, final Date toDate) {
        return getBookingRequests().stream().
                filter(bookingRequest -> bookingRequest.getStatus() == BookingRequestStatus.BOOKED)
                .noneMatch(bookingRequest -> !bookingRequest.getToDate().before(fromDate) && !toDate.before(bookingRequest.getFromDate()));

    }
}
