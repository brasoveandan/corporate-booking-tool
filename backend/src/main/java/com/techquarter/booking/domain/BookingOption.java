package com.techquarter.booking.domain;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class BookingOption {
    private final String id;
    private final ResourceType resourceType;
    private final String destination;
    private final String description;
    private final BigDecimal price;

    public BookingOption(String id, ResourceType resourceType, String destination, String description, BigDecimal price) {
        this.id = Objects.requireNonNull(id);
        this.resourceType = Objects.requireNonNull(resourceType);
        this.destination = Objects.requireNonNull(destination);
        this.description = Objects.requireNonNull(description);
        this.price = Objects.requireNonNull(price);
    }

    public static BookingOption of(ResourceType type, String destination, String description, BigDecimal price) {
        return new BookingOption(UUID.randomUUID().toString(), type, destination, description, price);
    }

    public String getId() {
        return id;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public String getDestination() {
        return destination;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
