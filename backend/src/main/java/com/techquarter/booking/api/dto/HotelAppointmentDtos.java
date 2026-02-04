package com.techquarter.booking.api.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class HotelAppointmentDtos {
    public static class CreateHotelAppointmentRequest {
        @NotBlank
        public String employeeId;
        @NotBlank
        public String hotelName;
        @NotNull
        @FutureOrPresent
        public LocalDate checkIn;
        @NotNull
        @FutureOrPresent
        public LocalDate checkOut;
    }

    public static class HotelAppointmentResponse {
        public String id;
        public String employeeId;
        public String hotelName;
        public LocalDate checkIn;
        public LocalDate checkOut;

        public HotelAppointmentResponse(String id, String employeeId, String hotelName, LocalDate checkIn, LocalDate checkOut) {
            this.id = id;
            this.employeeId = employeeId;
            this.hotelName = hotelName;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
        }
    }
}
