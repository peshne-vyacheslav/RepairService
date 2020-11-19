package com.example.repair.config;

import com.example.repair.dto.converter.OrderConverter;
import com.example.repair.dto.converter.UserConverter;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfig {

    @Bean
    public OrderConverter orderConverter() {
        return Mappers.getMapper(OrderConverter.class);
    }

    @Bean
    public UserConverter userConverter() {
        return Mappers.getMapper(UserConverter.class);
    }
}
