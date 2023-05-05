package com.proiectps.shopping.mapper;

import com.proiectps.shopping.dto.UserDTO;
import com.proiectps.shopping.model.User;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class UserMapper {

    public static UserDTO mapModelToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setIsAdmin(user.getIsAdmin());
        userDTO.setLoginDate(new Timestamp(System.currentTimeMillis()));
        return userDTO;
    }
}
