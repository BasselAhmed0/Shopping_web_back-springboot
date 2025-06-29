package com.shop.shop.Security;

import com.shop.shop.Entity.Role;
import com.shop.shop.Entity.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    User targetUser;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return setAuthorities(targetUser.getRoles());
    }

    private Collection<? extends GrantedAuthority> setAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


    @Override
    public String getPassword() {
        return targetUser.getPassword();
    }

    @Override
    public String getUsername() {
        return targetUser.getName();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true; // Change this logic based on your requirements
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Change this logic based on your requirements
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Change this logic based on your requirements
    }

    @Override
    public boolean isEnabled() {
        return true; // Change this logic based on your requirements
    }
}
