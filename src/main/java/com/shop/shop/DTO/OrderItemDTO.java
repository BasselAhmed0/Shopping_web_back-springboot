package com.shop.shop.DTO;

import com.shop.shop.Entity.Order;
import com.shop.shop.Entity.Product;
import lombok.Data;

@Data
public class OrderItemDTO {
    private Long orderId;
    private Long productId;
    private int quantity;
}


