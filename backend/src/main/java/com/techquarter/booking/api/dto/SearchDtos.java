package com.techquarter.booking.api.dto;

import com.techquarter.booking.domain.ResourceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class SearchDtos {
    public static class SearchRequest {
        @NotBlank
        public String employeeId;
        @NotNull
        public ResourceType resourceType;
        @NotBlank
        public String destination;
    }

    public static class BookingOptionResponse {
        public String id;
        public ResourceType resourceType;
        public String destination;
        public String description;
        public BigDecimal price;

        public BookingOptionResponse(String id, ResourceType resourceType, String destination, String description, BigDecimal price) {
            this.id = id;
            this.resourceType = resourceType;
            this.destination = destination;
            this.description = description;
            this.price = price;
        }
    }
}
