package com.shop.shop;

import com.shop.shop.Service.RoleService;
import com.shop.shop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAspectJAutoProxy
@SpringBootApplication
@EnableSwagger2

public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
//    @PostConstruct
//    public void initiateSystemUsers() {
//
//        Role adminRole = new Role("ROLE_ADMIN");
//        Role userRole = new Role("ROLE_USER");
//        Role viewerRole = new Role("ROLE_VIEWER");
//
//        List<Role> roleList = List.of(adminRole, userRole, viewerRole);
//        roleService.addAllRoles(roleList);
//
//
//
//        User user1 = new User("user0", "pwd0", "user0@mail.com");
//        user1.addRole(adminRole);
//        user1.addRole(userRole);
//        user1.addRole(viewerRole);
//
//        User user2 = new User("user2", "pwd2", "user2@mail.com");
//        user2.addRole(userRole);
//        user2.addRole(viewerRole);
//
//        User user3 = new User("user3", "pwd3", "user3@mail.com");
//        user3.addRole(viewerRole);
//
//        List<User> usersListList = List.of(user1, user2, user3);
//        userService.addAllUsers(usersListList);
//
//    }

//    @PostConstruct
//    public void init() {
//        initRoles();
//        initUsers();
//    }

//    private void initRoles() {
//        Optional<Role> adminRoleOptional = roleService.getRoleByName("ROLE_ADMIN");
//        Optional<Role> userRoleOptional = roleService.getRoleByName("ROLE_USER");
//        Optional<Role> viewerRoleOptional = roleService.getRoleByName("ROLE_VIEWER");
//
//        System.out.println("Admin Role Found: " + adminRoleOptional.isPresent());
//        System.out.println("User Role Found: " + userRoleOptional.isPresent());
//        System.out.println("Viewer Role Found: " + viewerRoleOptional.isPresent());
//
//        Role adminRole = adminRoleOptional.orElseThrow(() -> new RuntimeException("Admin role not found"));
//        Role userRole = userRoleOptional.orElseThrow(() -> new RuntimeException("User role not found"));
//        Role viewerRole = viewerRoleOptional.orElseThrow(() -> new RuntimeException("Viewer role not found"));
//
//        List<Role> roleList = List.of(adminRole, userRole, viewerRole);
//        roleService.addAllRoles(roleList);
//    }
//
//    private void initUsers() {
//        Role adminRole = roleService.getRoleByName("ROLE_ADMIN").orElseThrow(() -> new RuntimeException("Admin role not found"));
//
//        User user1 = new User("user0", passwordEncoder.encode("pwd0"), "user1@mail.com");
//        user1.addRole(adminRole);
//
//        User user2 = new User("user2", passwordEncoder.encode("pwd2"), "user2@mail.com");
//        user2.addRole(adminRole);
//
//        List<User> userList = List.of(user1, user2);
//        userService.addUsers(userList);
//    }
}





