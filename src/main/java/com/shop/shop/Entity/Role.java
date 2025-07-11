package com.shop.shop.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sec_roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "role_name", unique = true, nullable = false, length = 50)
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role() {

    }
}