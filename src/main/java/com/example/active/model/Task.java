package com.example.active.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @NotEmpty(message = "title can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private String title;
    private String description;
    @NotNull(message = "userID can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private Integer userID;
    @NotNull(message = "clubID can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private Integer clubID;
    //------------------------------------------chan
    @NotNull(message = "status can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private String status =  "IN PROGRESS";
}
