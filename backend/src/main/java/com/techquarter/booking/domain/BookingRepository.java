package com.techquarter.booking.domain;

import java.util.List;

public interface BookingRepository {
    Booking save(Booking booking);
    List<Booking> findByEmployeeId(String employeeId);
}
