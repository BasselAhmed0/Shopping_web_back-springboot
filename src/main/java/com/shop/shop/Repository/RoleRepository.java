package com.shop.shop.Repository;

import com.shop.shop.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByName(String roleName);

    Optional<Role> findByName(String name);
}
