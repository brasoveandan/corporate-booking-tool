package com.techquarter.booking.api;

import com.techquarter.booking.api.dto.BookingDtos.BookingResponse;
import com.techquarter.booking.api.dto.BookingDtos.CreateBookingRequest;
import com.techquarter.booking.application.BookingService;
import com.techquarter.booking.domain.Booking;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse create(@RequestBody @Valid CreateBookingRequest req) {
        Booking b = bookingService.createBooking(req.employeeId, req.resourceType,
                req.destination, req.departureDate, req.returnDate,
                req.travelerCount, req.costCenterRef, req.tripPurpose);
        return toResponse(b);
    }

    @GetMapping
    public List<BookingResponse> list(@RequestParam String employeeId) {
        return bookingService.listByEmployee(employeeId).stream()
                .map(this::toResponse)
                .toList();
    }

    private BookingResponse toResponse(Booking b) {
        return new BookingResponse(b.getId(), b.getEmployeeId(), b.getResourceType(),
                b.getDestination(), b.getDepartureDate(), b.getReturnDate(),
                b.getTravelerCount(), b.getCostCenterRef(), b.getTripPurpose(),
                b.getCreatedAt());
    }
}
