package com.techquarter.booking.domain;

import java.util.Optional;

public interface EmployeeRepository {
    Employee save(Employee employee);
    Optional<Employee> findById(String id);
}
