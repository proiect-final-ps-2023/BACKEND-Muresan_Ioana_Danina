package com.proiectps.shopping.service;

import com.proiectps.shopping.model.Review;

import java.util.List;

public interface ReviewService {
    Review createReview(Review review);
    Review getReviewById(Long id);
    List<Review> getAllReviews();
    Review updateReview(Long id, Review review);
    String deleteReview(Long id);
}
