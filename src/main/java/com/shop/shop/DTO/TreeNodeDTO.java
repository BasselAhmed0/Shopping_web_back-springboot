package com.shop.shop.DTO;

import lombok.Data;

import java.util.List;

@Data
public class TreeNodeDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Long parentId;

    private List<TreeNodeDTO> children;

    public void setChildren(List<TreeNodeDTO> children) {
        this.children = children;
    }
}
