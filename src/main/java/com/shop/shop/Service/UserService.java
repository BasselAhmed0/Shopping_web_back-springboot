package com.shop.shop.Service;

import com.shop.shop.DTO.TreeNodeDTO;
import com.shop.shop.Entity.User;
import com.shop.shop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository UserRepo) {
        this.userRepo = UserRepo;
    }

    public Optional<User> findUserById(Long id) {
        return userRepo.findById(id);
    }

    public Optional<User> findUserByName(String name) {
        return userRepo.findByName(name);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public List<User> addUsers(List<User> users) {
        return userRepo.saveAll(users);
    }

    public Iterable<User> addAllUsers(List<User> usersList) {
        usersList.forEach(user -> user.setPassword(passwordEncoder.encode(user.getPassword())));
        return userRepo.saveAll(usersList);
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User editUser(User user) {
        return userRepo.save(user);
    }

    public User updateUserName(Long userId, String newName) {
        User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        user.setName(newName);
        return userRepo.save(user);
    }

    public User updateUserEmail(Long userId, String newEmail) {
        User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        user.setPassword(newEmail);
        return userRepo.save(user);
    }


    public void deleteUser(Long id) {
        userRepo.deleteUserById(id);
    }


    public User convertToUser(TreeNodeDTO treeNodeDTO) {
        User user = new User();
        user.setName(treeNodeDTO.getName());
        user.setEmail(treeNodeDTO.getEmail());
        user.setPassword(treeNodeDTO.getPassword());

        if (treeNodeDTO.getChildren() != null) {
            List<User> children = new ArrayList<>();
            for (TreeNodeDTO childNode : treeNodeDTO.getChildren()) {
                User child = convertToUser(childNode);
                children.add(child);
            }
            user.setChildren(children);
        }

        return user;
    }
}

