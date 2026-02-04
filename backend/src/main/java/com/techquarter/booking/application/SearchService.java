package com.techquarter.booking.application;

import com.techquarter.booking.domain.BookingOption;
import com.techquarter.booking.domain.ResourceType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SearchService {

    public List<BookingOption> search(ResourceType resourceType, String destination) {
        // In lieu of external providers, return deterministic sample options
        if (resourceType == ResourceType.FLIGHT) {
            return List.of(
                    BookingOption.of(resourceType, destination, "Direct flight to " + destination, new BigDecimal("350.00")),
                    BookingOption.of(resourceType, destination, "1-stop flight to " + destination, new BigDecimal("280.00"))
            );
        } else {
            return List.of(
                    BookingOption.of(resourceType, destination, "3* Hotel in " + destination, new BigDecimal("120.00")),
                    BookingOption.of(resourceType, destination, "4* Hotel in " + destination, new BigDecimal("180.00"))
            );
        }
    }
}
