package com.shop.shop.Service;

import com.shop.shop.DTO.OrderItemDTO;
import com.shop.shop.Entity.Order;
import com.shop.shop.Entity.OrderItem;
import com.shop.shop.Entity.Product;
import com.shop.shop.Repository.OrderItemRepository;
import com.shop.shop.Repository.OrderRepository;
import com.shop.shop.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    private OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

//    public OrderItem createOrderItem(OrderItem orderItem) {
//        // Handle logic and validation
//        return orderItemRepository.save(orderItem);
//    }

    public OrderItem createOrderItem(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();

        // Find the Order and Product entities by their IDs
        Order order = orderRepository.findById(orderItemDTO.getOrderId()).orElse(null);
        Product product = productRepository.findById(orderItemDTO.getProductId()).orElse(null);

        if (order == null || product == null) {
            // Handle error (Order or Product not found)
            return null;
        }

        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(orderItemDTO.getQuantity());

        // Save the order item to the database
        return orderItemRepository.save(orderItem);
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderOrderId(orderId);
    }

    public List<OrderItem> getOrderItemsByProductId(Long productId) {
        return orderItemRepository.findByProductProductId(productId);
    }

    public OrderItem getOrderItemById(Long orderItemId) {
        return orderItemRepository.findById(orderItemId).orElse(null);
    }

    public OrderItem updateOrderItem(Long orderItemId, OrderItem updatedOrderItem) {
        Optional<OrderItem> existingOrderItemOptional = orderItemRepository.findById(orderItemId);
        if (existingOrderItemOptional.isPresent()) {
            OrderItem existingOrderItem = existingOrderItemOptional.get();
            existingOrderItem.setOrder(updatedOrderItem.getOrder());
            existingOrderItem.setProduct(updatedOrderItem.getProduct());
            existingOrderItem.setQuantity(updatedOrderItem.getQuantity());
            // You can update other properties as needed
            return orderItemRepository.save(existingOrderItem);
        }
        return null; // Return null if order item doesn't exist
    }

    public boolean deleteOrderItem(Long orderItemId) {
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(orderItemId);
        if (orderItemOptional.isPresent()) {
            orderItemRepository.delete(orderItemOptional.get());
            return true;
        }
        return false; // Return false if order item doesn't exist
    }

}
