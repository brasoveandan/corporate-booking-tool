package com.techquarter.booking.domain;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Booking {
    private final String id;
    private final String employeeId;
    private final ResourceType resourceType;
    private final String destination;
    private final LocalDateTime departureDate;
    private final LocalDateTime returnDate;
    private final Integer travelerCount;
    private final String costCenterRef;
    private final String tripPurpose;
    private final Instant createdAt;

    public Booking(String id, String employeeId, ResourceType resourceType,
                   String destination, LocalDateTime departureDate, LocalDateTime returnDate,
                   Integer travelerCount, String costCenterRef, String tripPurpose,
                   Instant createdAt) {
        this.id = Objects.requireNonNull(id);
        this.employeeId = Objects.requireNonNull(employeeId);
        this.resourceType = Objects.requireNonNull(resourceType);
        this.destination = Objects.requireNonNull(destination);
        this.departureDate = Objects.requireNonNull(departureDate);
        this.returnDate = Objects.requireNonNull(returnDate);
        this.travelerCount = Objects.requireNonNull(travelerCount);
        this.costCenterRef = Objects.requireNonNull(costCenterRef);
        this.tripPurpose = Objects.requireNonNull(tripPurpose);
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    public static Booking createNew(String employeeId, ResourceType resourceType,
                                    String destination, LocalDateTime departureDate, LocalDateTime returnDate,
                                    Integer travelerCount, String costCenterRef, String tripPurpose) {
        return new Booking(UUID.randomUUID().toString(), employeeId, resourceType,
                destination, departureDate, returnDate, travelerCount, costCenterRef, tripPurpose,
                Instant.now());
    }

    public String getId() { return id; }
    public String getEmployeeId() { return employeeId; }
    public ResourceType getResourceType() { return resourceType; }
    public String getDestination() { return destination; }
    public LocalDateTime getDepartureDate() { return departureDate; }
    public LocalDateTime getReturnDate() { return returnDate; }
    public Integer getTravelerCount() { return travelerCount; }
    public String getCostCenterRef() { return costCenterRef; }
    public String getTripPurpose() { return tripPurpose; }
    public Instant getCreatedAt() { return createdAt; }
}
