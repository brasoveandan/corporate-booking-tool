package com.techquarter.booking.application;

import com.techquarter.booking.domain.Booking;
import com.techquarter.booking.domain.BookingRepository;
import com.techquarter.booking.domain.ResourceType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final EmployeeService employeeService;

    public BookingService(BookingRepository bookingRepository, EmployeeService employeeService) {
        this.bookingRepository = bookingRepository;
        this.employeeService = employeeService;
    }

    public Booking createBooking(String employeeId, ResourceType resourceType,
                                  String destination, LocalDateTime departureDate, LocalDateTime returnDate,
                                  Integer travelerCount, String costCenterRef, String tripPurpose) {
        employeeService.requireEmployee(employeeId);
        Booking booking = Booking.createNew(employeeId, resourceType, destination,
                departureDate, returnDate, travelerCount, costCenterRef, tripPurpose);
        return bookingRepository.save(booking);
    }

    public List<Booking> listByEmployee(String employeeId) {
        employeeService.requireEmployee(employeeId);
        return bookingRepository.findByEmployeeId(employeeId);
    }
}
