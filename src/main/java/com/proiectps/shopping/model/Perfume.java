package com.proiectps.shopping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Fragrance type is required")
    private String fragrance_top_notes;

    @NotBlank(message = "Fragrance type is required")
    private String fragrance_middle_notes;

    @NotBlank(message = "Fragrance type is required")
    private String fragrance_base_notes;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be a positive number")
    private Integer price;

    private Integer new_price;

    @NotNull(message = "Volume is required")
    @Min(value = 0, message = "Volume must be a positive number")
    private Integer volume;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock must be a positive number")
    private Integer stock;

    @NotBlank(message = "Image is required")
    private String image;

    @OneToMany
    @ToString.Exclude
    private List<Review> reviews=new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private OrderItem orderItem;

    @ManyToOne
    private User user;
}
