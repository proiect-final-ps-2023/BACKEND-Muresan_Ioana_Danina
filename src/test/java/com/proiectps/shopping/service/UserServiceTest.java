package com.proiectps.shopping.service;

import com.proiectps.shopping.model.Review;
import com.proiectps.shopping.model.User;
import com.proiectps.shopping.repository.UserRepository;
import com.proiectps.shopping.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        User user = new User(userId, "John", "password", "john@example.com", false);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(userId);

        Assertions.assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void testCreateUser() {
        User user = new User( 1L,"John", "password", "john@example.com", false);
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        Assertions.assertNotNull(result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getPassword(), result.getPassword());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getIsAdmin(), result.getIsAdmin());
    }

    @Test
    void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "John", "password", "john@example.com", false));
        users.add(new User(2L, "Jane", "password", "jane@example.com", true));
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(users.size(), result.size());
        assertEquals(users.get(0), result.get(0));
        assertEquals(users.get(1), result.get(1));
    }

    @Test
    public void testUpdateUser() {
        // Create an initial user
        User user = new User(1L, "Test User", "password", "test@example.com", false);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        // Create an updated user
        User updatedUser = new User(1L, "Updated User", "newpassword", "updated@example.com", true);
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        User result = userService.updateUserInfo("test@example.com", updatedUser);

        assertEquals(updatedUser.getName(), result.getName());
        assertEquals(updatedUser.getEmail(), result.getEmail());
        assertEquals(updatedUser.getIsAdmin(), result.getIsAdmin());
        assertEquals(updatedUser.getPassword(), result.getPassword());
    }


    @Test
    void testDeleteUserById() {
        Long userId = 1L;
        String expectedMessage = "User deleted successfully";
        String result = userService.deleteUserById(userId);

        assertEquals(expectedMessage, result);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(userId);
    }
}
