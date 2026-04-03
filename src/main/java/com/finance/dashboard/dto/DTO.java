package com.finance.dashboard.dto;

import com.finance.dashboard.model.RecordType;
import com.finance.dashboard.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DTO {

    @Data
    public static class UserRequest {
        @NotBlank(message = "Name is required")
        private String name;
        @Email(message = "Valid email is required")
        @NotBlank(message = "Email is required")
        private String email;
        @NotBlank(message = "Password is required")
        private String password;
        @NotNull(message = "Role is required")
        private Role role;
    }

    @Data
    public static class UserResponse {
        private Long id;
        private String name;
        private String email;
        private Role role;
    }

    @Data
    public static class RecordRequest {
        @NotNull(message = "Type is required")
        private RecordType type;
        @NotBlank(message = "Category is required")
        private String category;
        @NotNull(message = "Amount is required")
        private BigDecimal amount;
        @NotNull(message = "Date is required")
        private LocalDate date;
        private String notes;
    }

    @Data
    public static class DashboardSummary {
        private BigDecimal totalIncome;
        private BigDecimal totalExpenses;
        private BigDecimal netBalance;
    }
}
