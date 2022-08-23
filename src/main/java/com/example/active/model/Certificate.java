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
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @NotEmpty(message = "name can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private String name;
    private String description;
    @NotNull(message = "userID can't be empty!")
    private Integer userID;
    @NotEmpty(message = "picURL can't be empty!")
    private String picURL;
}
