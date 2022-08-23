package com.example.active.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor @Data
public class UpdateBillStatus {
    @NotNull(message = "billID can't be empty!")
    private Integer billID;
    @NotEmpty(message = "status can't be empty!")
    private String status;
}
