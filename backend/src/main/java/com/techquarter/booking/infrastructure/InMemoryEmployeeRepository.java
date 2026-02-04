package com.techquarter.booking.infrastructure;

import com.techquarter.booking.domain.Employee;
import com.techquarter.booking.domain.EmployeeRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class InMemoryEmployeeRepository implements EmployeeRepository {
    private final ConcurrentMap<String, Employee> storage = new ConcurrentHashMap<>();

    @Override
    public Employee save(Employee employee) {
        storage.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public Optional<Employee> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }
}
