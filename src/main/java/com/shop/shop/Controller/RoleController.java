package com.shop.shop.Controller;

import com.shop.shop.Entity.Role;
import com.shop.shop.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;


    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<String> createRole(@RequestParam String roleName) {
        Role role = roleService.createRole(roleName);
        return ResponseEntity.ok("Role created with ID: " + role.getId());
    }

    @PostMapping("/assign/{roleId}")
    public ResponseEntity<String> assignRoleToUser(@PathVariable Long roleId, @RequestParam Long userId) {
        roleService.assignRoleToUser(roleId, userId);
        return ResponseEntity.ok("Role assigned successfully");
    }

    @GetMapping("/getRoles")
    public ResponseEntity<Iterable<Role>> getRoles() {
        Iterable<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }
}