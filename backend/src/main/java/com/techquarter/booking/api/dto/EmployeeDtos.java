package com.techquarter.booking.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmployeeDtos {
    public static class CreateEmployeeRequest {
        @NotBlank
        public String id;
        @NotBlank
        public String name;
        @Email
        @NotBlank
        public String email;
    }

    public static class EmployeeResponse {
        public String id;
        public String name;
        public String email;

        public EmployeeResponse(String id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }
    }
}
