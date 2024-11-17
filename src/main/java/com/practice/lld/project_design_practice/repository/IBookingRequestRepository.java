package com.practice.lld.project_design_practice.repository;

import com.practice.lld.project_design_practice.models.BookingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookingRequestRepository extends JpaRepository<BookingRequest, Long> {
}
