package com.shop.shop.DTO;

import com.shop.shop.Entity.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDTO {
    private User user;
    private int orderDate;
}