package com.shop.shop.Controller;


import com.shop.shop.DTO.OrderItemDTO;
import com.shop.shop.Entity.Order;
import com.shop.shop.Entity.OrderItem;
import com.shop.shop.Entity.Product;
import com.shop.shop.Exception.ResourceNotFoundException;
import com.shop.shop.Service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/orderItem")
public class OrderItemController {
    OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/by-order/{orderId}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByOrderId(@PathVariable Long orderId) {
        List<OrderItem> orderItems = orderItemService.getOrderItemsByOrderId(orderId);
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/by-product/{productId}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByProductId(@PathVariable Long productId) {
        List<OrderItem> orderItems = orderItemService.getOrderItemsByProductId(productId);
        return ResponseEntity.ok(orderItems);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        try {
            OrderItem createdOrderItem = orderItemService.createOrderItem(orderItemDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderItem);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{orderItemId}")
    public ResponseEntity<OrderItem> updateOrderItem(
            @PathVariable Long orderItemId,
            @RequestBody OrderItem orderItem
    ) {
        try {
            OrderItem updatedOrderItem = orderItemService.updateOrderItem(orderItemId, orderItem);
            return ResponseEntity.ok(updatedOrderItem);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long orderItemId) {
        boolean deleted = orderItemService.deleteOrderItem(orderItemId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("Order item not found with ID: " + orderItemId);
        }
    }
}