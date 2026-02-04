package com.techquarter.booking.infrastructure;

import com.techquarter.booking.domain.Booking;
import com.techquarter.booking.domain.BookingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class InMemoryBookingRepository implements BookingRepository {
    private final ConcurrentMap<String, List<Booking>> byEmployee = new ConcurrentHashMap<>();

    @Override
    public Booking save(Booking booking) {
        byEmployee.computeIfAbsent(booking.getEmployeeId(), k -> new ArrayList<>()).add(booking);
        return booking;
    }

    @Override
    public List<Booking> findByEmployeeId(String employeeId) {
        return new ArrayList<>(byEmployee.getOrDefault(employeeId, List.of()));
    }
}
