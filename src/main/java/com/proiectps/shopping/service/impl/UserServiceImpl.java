package com.proiectps.shopping.service.impl;

import com.proiectps.shopping.dto.PerfumeDTO;
import com.proiectps.shopping.dto.UserDTO;
import com.proiectps.shopping.mapper.PerfumeMapper;
import com.proiectps.shopping.model.Perfume;
import com.proiectps.shopping.model.User;
import com.proiectps.shopping.repository.OrderRepository;
import com.proiectps.shopping.repository.PerfumeRepository;
import com.proiectps.shopping.repository.UserRepository;
import com.proiectps.shopping.service.UserService;
import jakarta.transaction.Transactional;
import com.proiectps.shopping.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Autowired
    private OrderRepository orderRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(Long userId) {
       return userRepository.findById(userId);
   }

   @Override
   public UserDTO loginUser(String username, String password) {
       BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
         Optional<User> user = userRepository.findByUsername(username);
         user.ifPresent(value -> value.setLoginDate(new java.sql.Timestamp(System.currentTimeMillis())));
         userRepository.save(user.get());
       if(!user.get().getIsAdmin()) {
           if (passwordEncoder.matches(password, user.get().getPassword())) {
               return UserMapper.mapModelToDTO(user.get());
           }
           else if(user.get().getPassword().equals(password))
           {
               return UserMapper.mapModelToDTO(user.get());
           }
       }
       return null;
   }

   public void logoutUser(Long userId) {
         Optional<User> user = userRepository.findById(userId);
         user.ifPresent(value -> value.setLoginDate(null));
         userRepository.save(user.get());
   }

    @Override
    public UserDTO loginAdmin(String username, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<User> user = userRepository.findByUsername(username);
        user.ifPresent(value -> value.setLoginDate(new java.sql.Timestamp(System.currentTimeMillis())));
        userRepository.save(user.get());
        if(user.get().getIsAdmin()){
           if (passwordEncoder.matches(password, user.get().getPassword())) {
               return UserMapper.mapModelToDTO(user.get());
           }
        }
        return null;
    }

    public void encryptPassword(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.get().getPassword());
        user.get().setPassword(encodedPassword);
        userRepository.save(user.get());
    }

    public List<UserDTO> allLoggedUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream()
                .filter(user -> user.getLoginDate() != null && !user.getIsAdmin())
                .map(UserMapper::mapModelToDTO)
                .collect(Collectors.toList());
    }


    public User createUser(User user) {
        if(user.getUsername()== null || user.getPassword()== null || user.getEmail()== null || user.getName()== null)
        {
            throw new IllegalStateException("Username or password or email or name is null");
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent())
        {
            throw new IllegalStateException("Username already exists");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent())
        {
            throw new IllegalStateException("Email already exists");
        }
        return userRepository.save(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream()
                .map(UserMapper::mapModelToDTO)
                .collect(Collectors.toList());
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


    @Transactional
    public User updateUserById(Long id, User user) {
        User userFromDb = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userFromDb.setName(user.getName());
        userFromDb.setIsAdmin(user.getIsAdmin());
        userFromDb.setEmail(user.getEmail());
        userFromDb.setPassword(user.getPassword());
        userFromDb.setUsername(user.getUsername());

        return userFromDb;
    }

    public List<PerfumeDTO> addToFavorite(Long userId, Long perfumeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));
        Perfume perfume = perfumeRepository.findById(perfumeId).orElseThrow(() -> new IllegalArgumentException("Invalid perfume Id:" + perfumeId));
        user.getFavorite().add(perfume);
        userRepository.save(user);
        return user.getFavorite().stream()
                .map(PerfumeMapper::mapModelToDTO)
                .collect(Collectors.toList());
    }

    public List<PerfumeDTO> removeFromFavorite(Long userId, Long perfumeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));
        Perfume perfume = perfumeRepository.findById(perfumeId).orElseThrow(() -> new IllegalArgumentException("Invalid perfume Id:" + perfumeId));
        user.getFavorite().remove(perfume);
        userRepository.save(user);
        return user.getFavorite().stream()
                .map(PerfumeMapper::mapModelToDTO)
                .collect(Collectors.toList());
    }

    public List<PerfumeDTO> getFavorite(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid userId" + userId));
        return user.getFavorite().stream()
                .map(PerfumeMapper::mapModelToDTO)
                .collect(Collectors.toList());
    }

    public PerfumeDTO updatePrice(Long perfumeId, Integer newPrice) {
        Perfume perfume = perfumeRepository.findById(perfumeId).orElseThrow(() -> new IllegalArgumentException("Invalid perfume Id:" + 1L));
        perfume.setNew_price(newPrice);
        perfumeRepository.save(perfume);
        return PerfumeMapper.mapModelToDTO(perfume);
    }

    public Map<Long,Long> createMap(Long perfumeId, Integer quantity, Long userId) {
        Map<Long,Long> map = new HashMap<>();
        map.put(perfumeId, (long) quantity);
        if(perfumeRepository.findById(perfumeId).isPresent()) {
            if (userRepository.findUserById(userId).getPerfumesIdAndQuantity() != null)
                userRepository.findUserById(userId).getPerfumesIdAndQuantity().put(perfumeId, (long) quantity);
            else
                userRepository.findUserById(userId).setPerfumesIdAndQuantity(map);
            userRepository.save(userRepository.findUserById(userId));
        }
        else throw new IllegalArgumentException("Invalid perfume Id:" + perfumeId);
        return userRepository.findUserById(userId).getPerfumesIdAndQuantity();
    }

    public Map<Long,Long> getMap(Long userId) {
        return userRepository.findUserById(userId).getPerfumesIdAndQuantity();
    }

    public Map<Long, Long> reinitializeMap(Long userId) {
        userRepository.findUserById(userId).setPerfumesIdAndQuantity(null);
        userRepository.save(userRepository.findUserById(userId));
        return null;
    }

    public void setTransport(boolean flag) {
        orderRepository.findAll().forEach(order -> order.setTransport(flag));
        orderRepository.saveAll(orderRepository.findAll());
    }


}
