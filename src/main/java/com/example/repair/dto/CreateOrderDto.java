package com.example.repair.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateOrderDto {

    @NotBlank
    private String description;
}
