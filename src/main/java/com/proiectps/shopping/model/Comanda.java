package com.proiectps.shopping.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@Builder
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer total_price;
    private String city;
    private String address;
    private String phone_number;
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    @OneToMany(fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    public Comanda() {
        this.date = LocalDate.now();
        this.orderItems = new ArrayList<>();
    }

    public Comanda(Integer total_price, String city, String address, String phone_number, LocalDate date, User user, List<OrderItem> orderItems) {
        this.total_price = total_price;
        this.city = city;
        this.address = address;
        this.phone_number = phone_number;
        this.date = date;
        this.user = user;
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comanda order = (Comanda) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
