package com.shop.shop.Service;

import com.shop.shop.Entity.Role;
import com.shop.shop.Entity.User;
import com.shop.shop.Repository.RoleRepository;
import com.shop.shop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Role createRole(String roleName) {
        Role role = new Role(roleName);
        return roleRepository.save(role);
    }

    @Transactional
    public void assignRoleToUser(Long roleId, Long userId) {
        Role role = roleRepository.findById(roleId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (role != null && user != null) {
            user.addRole(role);
            userRepository.save(user);
        }
    }

    public Iterable<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Iterable<Role> addAllRoles(List<Role> rolesList) {
        return roleRepository.saveAll(rolesList);
    }
}
