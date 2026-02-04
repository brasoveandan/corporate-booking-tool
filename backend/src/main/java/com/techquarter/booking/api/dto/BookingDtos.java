package com.techquarter.booking.api.dto;

import com.techquarter.booking.domain.ResourceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.Instant;
import java.time.LocalDateTime;

public class BookingDtos {
    public static class CreateBookingRequest {
        @NotBlank
        public String employeeId;
        @NotNull
        public ResourceType resourceType;
        @NotBlank
        public String destination;
        @NotNull
        public LocalDateTime departureDate;
        @NotNull
        public LocalDateTime returnDate;
        @NotNull
        @Positive
        public Integer travelerCount;
        @NotBlank
        public String costCenterRef;
        @NotBlank
        public String tripPurpose;
    }

    public static class BookingResponse {
        public String id;
        public String employeeId;
        public ResourceType resourceType;
        public String destination;
        public LocalDateTime departureDate;
        public LocalDateTime returnDate;
        public Integer travelerCount;
        public String costCenterRef;
        public String tripPurpose;
        public Instant createdAt;

        public BookingResponse(String id, String employeeId, ResourceType resourceType,
                               String destination, LocalDateTime departureDate, LocalDateTime returnDate,
                               Integer travelerCount, String costCenterRef, String tripPurpose,
                               Instant createdAt) {
            this.id = id;
            this.employeeId = employeeId;
            this.resourceType = resourceType;
            this.destination = destination;
            this.departureDate = departureDate;
            this.returnDate = returnDate;
            this.travelerCount = travelerCount;
            this.costCenterRef = costCenterRef;
            this.tripPurpose = tripPurpose;
            this.createdAt = createdAt;
        }
    }
}
