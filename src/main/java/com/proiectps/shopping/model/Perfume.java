package com.proiectps.shopping.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Perfume {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String gender;
    private String brand;
    private String fragrance_top_notes;
    private String fragrance_middle_notes;
    private String fragrance_base_notes;
    private String description;
    private Integer price;
    private Integer new_price;
    private Integer volume;
    private Integer stock;
    private String image;

    @OneToMany
    @ToString.Exclude
    private List<Review> reviews=new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private OrderItem orderItem;

    @ManyToOne
    private User user;
}
