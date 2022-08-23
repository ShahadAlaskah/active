package com.example.active.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class FindAllByClubIDAndStatus {
    @NotNull
    private Integer clubID;
    @NotNull
    private String status;
}
