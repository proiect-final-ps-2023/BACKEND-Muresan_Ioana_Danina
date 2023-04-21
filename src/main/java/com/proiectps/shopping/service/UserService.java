package com.proiectps.shopping.service;

import com.proiectps.shopping.dto.UserDTO;
import com.proiectps.shopping.model.Review;
import com.proiectps.shopping.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(Long userId);
    UserDTO loginUser(String username, String password);
    UserDTO loginAdmin(String username, String password);
    User createUser(User user);
    List<UserDTO> getAllUsers();
    User updateUserInfo(String email, User user);
    String deleteUserById(Long userId);

}
