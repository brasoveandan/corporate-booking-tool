package com.techquarter.booking.infrastructure;

import com.techquarter.booking.domain.HotelAppointment;
import com.techquarter.booking.domain.HotelAppointmentRepository;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class InMemoryHotelAppointmentRepository implements HotelAppointmentRepository {
    private final ConcurrentMap<String, HotelAppointment> storage = new ConcurrentHashMap<>();

    @Override
    public HotelAppointment save(HotelAppointment appointment) {
        storage.put(appointment.getId(), appointment);
        return appointment;
    }
}
