package com.proiectps.shopping.service;

import com.proiectps.shopping.model.Review;
import com.proiectps.shopping.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(Long userId);
    User createUser(User user);
    List<User> getAllUsers();
    User updateUserInfo(String email, User user);
    String deleteUserById(Long userId);
}
