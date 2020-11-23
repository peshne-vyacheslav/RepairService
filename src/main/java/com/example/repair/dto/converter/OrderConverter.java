package com.example.repair.dto.converter;

import com.example.repair.dto.CreateOrderDto;
import com.example.repair.dto.OrderDto;
import com.example.repair.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderConverter {

    @Mapping(source = "user.name", target = "user")
    OrderDto toOrderDto(Order order);

    Order toOrder(CreateOrderDto createOrderDto);
}
