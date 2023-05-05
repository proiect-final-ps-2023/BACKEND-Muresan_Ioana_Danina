package com.proiectps.shopping.controller;

import com.proiectps.shopping.dto.ComandaDTO;
import com.proiectps.shopping.dto.PerfumeDTO;
import com.proiectps.shopping.dto.ReviewDTO;
import com.proiectps.shopping.dto.UserDTO;
import com.proiectps.shopping.model.Comanda;
import com.proiectps.shopping.model.Perfume;
import com.proiectps.shopping.model.Review;
import com.proiectps.shopping.model.User;
import com.proiectps.shopping.service.impl.ComandaServiceImpl;
import com.proiectps.shopping.service.impl.PerfumeServiceImpl;
import com.proiectps.shopping.service.impl.ReviewServiceImpl;
import com.proiectps.shopping.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
    private final UserServiceImpl userService;
    private final ComandaServiceImpl comandaService;
    private final PerfumeServiceImpl perfumeService;
    private final ReviewServiceImpl reviewService;

    @PutMapping("/create")
    public User createAdmin(@RequestBody User user)
    {
        user.setIsAdmin(true);
        return userService.createUser(user);
    }

    @PutMapping("/update/{id}")
    public User updateAdminInfo(@PathVariable Long id, @RequestBody User user)
    {
        return userService.updateUserById(id,user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id)
    {
        return userService.deleteUserById(id);
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers() { return userService.getAllUsers();}

    @GetMapping("/allOrders")
    public List<ComandaDTO> getAllOrders() { return comandaService.getAllOrders();}

    @DeleteMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable Long id) { return comandaService.deleteOrder(id);}

    @PutMapping("/addPerfume")
    public Perfume addPerfume(@RequestBody Perfume perfume) { return perfumeService.savePerfume(perfume);}

    @DeleteMapping("/deletePerfume/{id}")
    public String deletePerfume(@PathVariable Long id) { return perfumeService.deletePerfume(id);}

    @PutMapping("/updatePerfume/{id}")
    public Perfume updatePerfume(@PathVariable Long id, @RequestBody Perfume perfume) { return perfumeService.updatePerfume(id, perfume);}

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody User user) {
        if( userService.loginAdmin(user.getUsername(), user.getPassword()) == null)
            return (ResponseEntity<UserDTO>) ResponseEntity.internalServerError();
        else return ResponseEntity.ok(userService.loginAdmin(user.getUsername(), user.getPassword()));
    }

    @GetMapping("/reviews/{id}")
    public List<ReviewDTO> getReviewsByPerfumeId(@PathVariable Long id) { return reviewService.getAllReviewsByPerfumeId(id);}

    @PostMapping("/updatePrice/{perfumeId}/{price}")
    public PerfumeDTO updatePrice(@PathVariable Long perfumeId, @PathVariable Integer price) {
        return userService.updatePrice(perfumeId,price);
    }

    @GetMapping("/allLoggedIn")
    public List<UserDTO> getAllLoggedInUsers() {
        return userService.allLoggedUsers();
    }

    @PostMapping("/transport/{flag}")
    public void updateTransport(@PathVariable String flag) {
        userService.setTransport(Boolean.parseBoolean(flag));
    }


}
