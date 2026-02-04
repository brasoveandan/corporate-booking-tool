package com.techquarter.booking.application;

import com.techquarter.booking.domain.HotelAppointment;
import com.techquarter.booking.domain.HotelAppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class HotelAppointmentService {
    private final HotelAppointmentRepository repository;
    private final EmployeeService employeeService;

    public HotelAppointmentService(HotelAppointmentRepository repository, EmployeeService employeeService) {
        this.repository = repository;
        this.employeeService = employeeService;
    }

    public HotelAppointment create(String employeeId, String hotelName, LocalDate checkIn, LocalDate checkOut) {
        employeeService.requireEmployee(employeeId);
        HotelAppointment appointment = HotelAppointment.createNew(employeeId, hotelName, checkIn, checkOut);
        return repository.save(appointment);
    }
}
