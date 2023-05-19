package com.proiectps.shopping.controller;

import com.proiectps.shopping.dto.ComandaDTO;
import com.proiectps.shopping.dto.PerfumeDTO;
import com.proiectps.shopping.dto.UserDTO;
import com.proiectps.shopping.model.*;
import com.proiectps.shopping.repository.UserRepository;
import com.proiectps.shopping.service.impl.ComandaServiceImpl;
import com.proiectps.shopping.service.impl.EmailService;
import com.proiectps.shopping.service.impl.ReviewServiceImpl;
import com.proiectps.shopping.service.impl.UserServiceImpl;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserServiceImpl userService;
    private final ReviewServiceImpl reviewService;
    private final ComandaServiceImpl comandaService;
    private final UserRepository userRepository;
    private final EmailService emailSenderService;

     @GetMapping("/info/{id}")
    public String getUserById(@PathVariable Long id) {
        return userService.getUserById(id).toString();
    }

    @GetMapping("/comenzi/{id}")
    public String getComenziByUserId(@PathVariable Long id) {
        return comandaService.getComenziByUserId(id).toString();
    }

    @PutMapping("/update/{id}")
    public String updateUserInfo(@PathVariable Long id, @RequestBody User user) {
         return userService.updateUserById(id,user).toString();
     }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
         return userService.getAllUsers();
     }

    @PutMapping("/create")
    public User createUser(@RequestBody User user) {
         user.setIsAdmin(false);
         BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
         String encryptedPass= passwordEncoder.encode(user.getPassword());
         user.setPassword(encryptedPass);
         return userService.createUser(user);
     }

    @PutMapping("/encryptPassword/{id}")
    public void encryptPassword(@PathVariable Long id) {
         userService.encryptPassword(id);
     }

    @PostMapping("/review/{idPerfume}")
    public String createReview(@PathVariable Long idPerfume, @RequestBody Review review) {
         return reviewService.createReview(idPerfume,review).toString();
     }

    @PutMapping("/order/{userId}/{city}/{address}/{phone}")
    public ComandaDTO postComanda(@PathVariable Long userId, @PathVariable String city,@PathVariable String address, @PathVariable String phone) {
        return comandaService.postOrder(phone,city, address,userRepository.findUserById(userId).getPerfumesIdAndQuantity() , userId);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody User user) {
         if( userService.loginUser(user.getUsername(), user.getPassword()) == null)
           return (ResponseEntity<UserDTO>) ResponseEntity.internalServerError();
         else
           return ResponseEntity.ok(userService.loginUser(user.getUsername(), user.getPassword()));
    }


    @PostMapping("/map/{idPerfume}/{quantity}/{userId}")
    public Map<Long,Long> createMap(@PathVariable Long idPerfume, @PathVariable Integer quantity, @PathVariable Long userId) {
        return userService.createMap(idPerfume,quantity,userId);
    }

    @GetMapping("/getMap/{userId}")
    public Map<Long,Long> getMap(@PathVariable Long userId) {
         return userService.getMap(userId);
    }

    @PostMapping("/removeMap/{userId}")
    public Map<Long,Long> removeMap(@PathVariable Long userId) {
         return userService.reinitializeMap(userId);
    }


    @PostMapping("/favorite/{userId}/{perfumeId}")
    public List<PerfumeDTO> addFavorite(@PathVariable Long userId, @PathVariable Long perfumeId) {
        return userService.addToFavorite(userId,perfumeId);
    }

    @DeleteMapping("/removeFavorite/{userId}/{perfumeId}")
    public List<PerfumeDTO> removeFavorite(@PathVariable Long userId, @PathVariable Long perfumeId) {
        return userService.removeFromFavorite(userId,perfumeId);
    }

    @GetMapping("/allFavorite/{userId}")
    public List<PerfumeDTO> getFavorite(@PathVariable Long userId) {
         return userService.getFavorite(userId);
    }

    @PostMapping("/logoutUser/{userId}")
    public void logoutUser(@PathVariable Long userId) {
         userService.logoutUser(userId);
    }

    @PostMapping("/sendEmail/{email}")
    public ResponseEntity<String> sendEmail(@PathVariable String email) throws MessagingException {
        emailSenderService.sendMailWithAttachment(email,
                "Hello!"+
                        """
                       
                        Thank you for creating an account on The Perfume City! We're excited to have you as part of our community of perfume lovers.

                        At The Perfume City, you'll discover a world of captivating fragrances and luxurious scents. We're dedicated to providing you with a delightful perfume shopping experience.

                        Here's what you can expect as a member:

                        --Explore our extensive collection of perfumes from renowned brands.
                        --Get personalized recommendations based on your preferences.
                        --Enjoy exclusive offers and promotions.
                        --Receive expert advice to help you find your perfect scent.
                        --Engage with fellow perfume enthusiasts in our community forums.
                        We're here to assist you every step of the way.
                        If you have any questions or need assistance, feel free to reach out to our friendly support team.

                        Once again, welcome to The Perfume City!
                        Start your perfume journey today and indulge in the enchanting world of scents.

                        Best regards,
                        Ioana Muresan from
                        The Perfume City Team""" ,
                "Welcome to our website!");
       return ResponseEntity.ok("Email sent successfully");
    }



}
