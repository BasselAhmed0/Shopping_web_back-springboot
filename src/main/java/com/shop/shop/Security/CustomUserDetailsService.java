package com.shop.shop.Security;

import com.shop.shop.Entity.User;
import com.shop.shop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> targetUser = userService.findUserByName(username);
        if (targetUser.isPresent()) {
            return new CustomUserDetails(targetUser.get());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}
