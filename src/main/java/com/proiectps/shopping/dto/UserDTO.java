package com.proiectps.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long id;
    public String name;
    public String username;
    public String password;
    public String email;
    public Boolean isAdmin;
}
