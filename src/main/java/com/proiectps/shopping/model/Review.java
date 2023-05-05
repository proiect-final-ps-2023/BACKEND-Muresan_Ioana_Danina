package com.proiectps.shopping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "Message is required")
    private String message;

    private LocalDate date;

    @NotNull(message = "Rating is required")
    @Min(value = 0, message = "Rating must be a between 0 and 5 inclusive")
    @Max(value = 5, message = "Rating must be a between 0 and 5 inclusive")
    private Integer rating;

    @ManyToOne
    private Perfume perfume;

    public Review() {
        this.date = LocalDate.now();
    }

    public Review(String author, String message, LocalDate date, Integer rating) {
        this.author = author;
        this.message = message;
        this.date = date;
        this.rating = rating;
    }

    public Review(String author, String message, Integer rating, Perfume perfume) {
        this.author = author;
        this.message = message;
        this.rating = rating;
        this.date = LocalDate.now();
        this.perfume = perfume;
    }
}
