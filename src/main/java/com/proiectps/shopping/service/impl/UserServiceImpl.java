package com.proiectps.shopping.service.impl;

import com.proiectps.shopping.model.User;
import com.proiectps.shopping.repository.UserRepository;
import com.proiectps.shopping.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(Long userId)
   {
       return userRepository.findById(userId);
   }

    public User createUser(User user)
        {
           return userRepository.save(user);
        }

    public List<User> getAllUsers() {
       return (List<User>) userRepository.findAll();
    }
    @Override
    @Transactional
    public User updateUserInfo(String email, User user) {
        User userFromDb = (User) userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user email:" + email));
        userFromDb.setName(user.getName());
        userFromDb.setIsAdmin(user.getIsAdmin());
        userFromDb.setEmail(user.getEmail());
        userFromDb.setPassword(user.getPassword());

        return userFromDb;
    }
    public String deleteUserById(Long userId)
    {
        userRepository.deleteById(userId);
        return "User deleted successfully";
    }
}
