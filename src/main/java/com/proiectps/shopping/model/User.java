package com.proiectps.shopping.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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

    private String name;
    private String username;
    private String password;
    private String email;
    private Boolean isAdmin;

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
