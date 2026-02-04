package com.techquarter.booking.api;

import com.techquarter.booking.api.dto.SearchDtos.BookingOptionResponse;
import com.techquarter.booking.api.dto.SearchDtos.SearchRequest;
import com.techquarter.booking.application.EmployeeService;
import com.techquarter.booking.application.SearchService;
import com.techquarter.booking.domain.BookingOption;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;
    private final EmployeeService employeeService;

    public SearchController(SearchService searchService, EmployeeService employeeService) {
        this.searchService = searchService;
        this.employeeService = employeeService;
    }

    @PostMapping
    public List<BookingOptionResponse> search(@RequestBody @Valid SearchRequest req) {
        // Ensure employee exists; throws if not found
        employeeService.requireEmployee(req.employeeId);
        List<BookingOption> options = searchService.search(req.resourceType, req.destination);
        return options.stream()
                .map(o -> new BookingOptionResponse(o.getId(), o.getResourceType(), o.getDestination(), o.getDescription(), o.getPrice()))
                .toList();
    }
}
