package com.proiectps.shopping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;

    @NotNull(message = "isAdmin is required")
    private Boolean isAdmin;

    private Timestamp loginDate;

    @OneToMany(cascade = CascadeType.ALL,fetch= FetchType.EAGER)
    private List<Perfume> favorite;

    @OneToMany(cascade = CascadeType.ALL,fetch= FetchType.EAGER, mappedBy = "id")
    private List<Comanda> ordersList;

    @OneToMany(cascade = CascadeType.ALL,fetch= FetchType.EAGER, mappedBy = "id")
    private List<OrderItem> orderItemsList;

    @ElementCollection
    private Map<Long, Long> perfumesIdAndQuantity= new HashMap<Long,Long>();

    public User(String name, String password, String email, Boolean isAdmin) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }
    public User(Long id, String name, String password, String email, Boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
