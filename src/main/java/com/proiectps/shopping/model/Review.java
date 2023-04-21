package com.proiectps.shopping.model;

import jakarta.persistence.*;
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

    private String author;
    private String message;
    private LocalDate date;
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
