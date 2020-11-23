package com.example.repair.rest;

import com.example.repair.dto.CreateOrderDto;
import com.example.repair.dto.OrderDto;
import com.example.repair.dto.converter.OrderConverter;
import com.example.repair.entity.Order;
import com.example.repair.entity.User;
import com.example.repair.security.UserPrincipal;
import com.example.repair.service.OrderService;
import com.example.repair.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;
    private final UserService userService;

    @GetMapping("/orders")
    public List<OrderDto> getOrders(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return orderService.getOrders(userPrincipal.getUserId(), userPrincipal.getRoles())
                .stream()
                .map(orderConverter::toOrderDto)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@Valid @RequestBody CreateOrderDto orderDto, Authentication authentication) {
        Order order = orderConverter.toOrder(orderDto);
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        User user = userService.getUserByLogin(userDetails.getUsername());
        order.setUser(user);
        order = orderService.createOrder(order);
        return orderConverter.toOrderDto(order);
    }

    @Secured("ADMIN")
    @DeleteMapping(value = "/orders/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }

    @Secured("ADMIN")
    @PutMapping(value = "/orders/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto update(@PathVariable Long orderId, @Valid @RequestBody CreateOrderDto orderDto) {
        Order order = orderConverter.toOrder(orderDto);
        order = orderService.updateOrder(orderId, order);
        return orderConverter.toOrderDto(order);
    }
}
