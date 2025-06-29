package com.shop.shop.Service;

import com.shop.shop.Entity.Order;
import com.shop.shop.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrder(Long orderId, Order updatedOrder) {
        Optional<Order> existingOrderOptional = orderRepository.findById(orderId);
        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();
            existingOrder.setUser(updatedOrder.getUser());
            existingOrder.setOrderDate(updatedOrder.getOrderDate());
            existingOrder.setProducts(updatedOrder.getProducts());
            // You can update other properties as needed
            return orderRepository.save(existingOrder);
        }
        return null; // Return null if order doesn't exist
    }

    public boolean deleteOrder(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            orderRepository.delete(orderOptional.get());
            return true;
        }
        return false; // Return false if order doesn't exist
    }

}
