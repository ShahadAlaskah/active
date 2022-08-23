package com.example.active.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor @NoArgsConstructor @Data
@Entity
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @NotNull(message = "userID can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private Integer userID;
    @NotNull(message = "clubID can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private Integer clubID;
    private String name;
}
