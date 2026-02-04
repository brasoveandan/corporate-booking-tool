package com.techquarter.booking.api;

import com.techquarter.booking.api.dto.EmployeeDtos.CreateEmployeeRequest;
import com.techquarter.booking.api.dto.EmployeeDtos.EmployeeResponse;
import com.techquarter.booking.application.EmployeeService;
import com.techquarter.booking.domain.Employee;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse create(@RequestBody @Valid CreateEmployeeRequest req) {
        Employee e = employeeService.register(req.id, req.name, req.email);
        return new EmployeeResponse(e.getId(), e.getName(), e.getEmail());
    }
}
