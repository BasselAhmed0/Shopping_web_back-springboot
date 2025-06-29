package com.shop.shop.Controller;

import com.shop.shop.DTO.TreeNodeDTO;
import com.shop.shop.Entity.User;
import com.shop.shop.Exception.CustomResponse;
import com.shop.shop.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/registration")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/greet")
    public ResponseEntity<String> greet() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.editUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping("/{userId}/name")
    public ResponseEntity<CustomResponse> updateUserName(@PathVariable Long userId, @RequestBody String newName) {
        try {
            User updatedUser = userService.updateUserName(userId, newName);
            String successMessage = "User name updated successfully.";
            CustomResponse response = new CustomResponse(successMessage, HttpStatus.OK);
            return ResponseEntity.ok(response);
        } catch (NotFoundException e) {
            String errorMessage = "User not found.";
            CustomResponse response = new CustomResponse(errorMessage, HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            String errorMessage = "Internal server error.";
            CustomResponse response = new CustomResponse(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{userId}/email")
    public ResponseEntity<User> updateUserEmail(@PathVariable Long userId, @RequestBody String newEmail) {
        try {
            User updatedUser = userService.updateUserEmail(userId, newEmail);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create-bulk")
    public ResponseEntity<List<User>> createBulkUsers(@RequestBody List<TreeNodeDTO> userDTOs) {
        List<User> users = userDTOs.stream().map(userService::convertToUser).collect(Collectors.toList());
        userService.addUsers(users);
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        Optional<User> user = userService.findUserByName(name);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
