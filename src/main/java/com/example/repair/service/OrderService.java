package com.example.repair.service;

import com.example.repair.entity.Order;
import com.example.repair.entity.enums.Role;
import com.example.repair.exception.OrderNotFoundException;
import com.example.repair.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> getOrders(Long userId, Set<Role> userRoles) {
        if (userRoles.contains(Role.ADMIN)) {
            return orderRepository.findAll();
        }
        if (userRoles.contains(Role.CUSTOMER)) {
            return orderRepository.getOrderByUserId(userId);
        }

        throw new RuntimeException("Invalid user with roles " + userId);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }


    public void deleteOrder(Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {
            throw new OrderNotFoundException(orderId);
        }
    }

    public Order updateOrder(Long orderId, Order order) {
        Order existedOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        existedOrder.setDescription(order.getDescription());
        return orderRepository.save(existedOrder);
    }
}
