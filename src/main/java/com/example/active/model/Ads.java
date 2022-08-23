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
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @NotEmpty(message = "title can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private String title;
    private String description;
    private String files;
    @NotNull(message = "clubID can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private Integer clubID;

}
