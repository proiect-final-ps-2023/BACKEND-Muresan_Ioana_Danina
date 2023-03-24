package com.proiectps.shopping.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public Review() {
        this.date = LocalDate.now();
    }


    public Review(String author, String message, LocalDate date, Integer rating) {
        this.author = author;
        this.message = message;
        this.date = date;
        this.rating = rating;
    }
}
