package com.techquarter.booking.application;

import com.techquarter.booking.domain.ResourceType;
import com.techquarter.booking.infrastructure.InMemoryBookingRepository;
import com.techquarter.booking.infrastructure.InMemoryEmployeeRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    @Test
    void happyPath_registerEmployee_and_createBooking() {
        var employeeRepo = new InMemoryEmployeeRepository();
        var bookingRepo = new InMemoryBookingRepository();
        var employeeService = new EmployeeService(employeeRepo);
        var bookingService = new BookingService(bookingRepo, employeeService);

        employeeService.register("E1", "Jane Doe", "jane@corp.com");
        var booking = bookingService.createBooking("E1", ResourceType.FLIGHT,
                "NYC", LocalDateTime.of(2024, 11, 5, 8, 0),
                LocalDateTime.of(2024, 11, 8, 18, 0),
                1, "CC-456", "Client meeting - Acme Corp");

        assertNotNull(booking.getId());
        assertEquals("E1", booking.getEmployeeId());
        assertEquals(ResourceType.FLIGHT, booking.getResourceType());
        assertEquals("NYC", booking.getDestination());
        assertEquals(1, booking.getTravelerCount());

        var bookings = bookingService.listByEmployee("E1");
        assertEquals(1, bookings.size());
    }
}
