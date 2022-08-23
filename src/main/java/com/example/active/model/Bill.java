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
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @NotEmpty(message = "name can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private String name;
    @NotEmpty(message = "seller can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private String seller;
    @NotEmpty(message = "picURL can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private String picURL;
    //حق الماليه يغير الستيت
    private String status;
    @NotNull(message = "amount can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private Float amount;
    @NotNull(message = "clubID can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private Integer clubID;
}
