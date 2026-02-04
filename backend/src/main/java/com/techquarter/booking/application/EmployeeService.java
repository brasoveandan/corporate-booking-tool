package com.techquarter.booking.application;

import com.techquarter.booking.domain.Employee;
import com.techquarter.booking.domain.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee register(String id, String name, String email) {
        Employee employee = new Employee(id, name, email);
        return employeeRepository.save(employee);
    }

    public Employee requireEmployee(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found: " + id));
    }
}
