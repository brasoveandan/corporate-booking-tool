package com.techquarter.booking.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class HotelAppointment {
    private final String id;
    private final String employeeId;
    private final String hotelName;
    private final LocalDate checkIn;
    private final LocalDate checkOut;

    public HotelAppointment(String id, String employeeId, String hotelName, LocalDate checkIn, LocalDate checkOut) {
        this.id = Objects.requireNonNull(id);
        this.employeeId = Objects.requireNonNull(employeeId);
        this.hotelName = Objects.requireNonNull(hotelName);
        this.checkIn = Objects.requireNonNull(checkIn);
        this.checkOut = Objects.requireNonNull(checkOut);
    }

    public static HotelAppointment createNew(String employeeId, String hotelName, LocalDate checkIn, LocalDate checkOut) {
        return new HotelAppointment(UUID.randomUUID().toString(), employeeId, hotelName, checkIn, checkOut);
    }

    public String getId() {
        return id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }
}
