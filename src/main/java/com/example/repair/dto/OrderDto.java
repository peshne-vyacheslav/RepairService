package com.example.repair.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDto {
    private Long id;
    private String description;
    private LocalDate created;
    private String user;
}
