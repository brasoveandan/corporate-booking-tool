package com.techquarter.booking.api;

import com.techquarter.booking.api.dto.HotelAppointmentDtos.CreateHotelAppointmentRequest;
import com.techquarter.booking.api.dto.HotelAppointmentDtos.HotelAppointmentResponse;
import com.techquarter.booking.application.HotelAppointmentService;
import com.techquarter.booking.domain.HotelAppointment;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel-appointments")
public class HotelAppointmentController {
    private final HotelAppointmentService service;

    public HotelAppointmentController(HotelAppointmentService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HotelAppointmentResponse create(@RequestBody @Valid CreateHotelAppointmentRequest req) {
        HotelAppointment ha = service.create(req.employeeId, req.hotelName, req.checkIn, req.checkOut);
        return new HotelAppointmentResponse(ha.getId(), ha.getEmployeeId(), ha.getHotelName(), ha.getCheckIn(), ha.getCheckOut());
    }
}
