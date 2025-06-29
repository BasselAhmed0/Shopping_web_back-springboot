package com.shop.shop.Repository;

import com.shop.shop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    void deleteUserById(Long id);
}
