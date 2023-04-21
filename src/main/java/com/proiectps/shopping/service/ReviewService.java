package com.proiectps.shopping.service;

import com.proiectps.shopping.dto.ReviewDTO;
import com.proiectps.shopping.model.Review;

import java.util.List;

public interface ReviewService {


    Review createReview(Long perfumeId, Review review);
    Review getReviewById(Long id);
    Review updateReview(Long id, Review review);
    String deleteReview(Long id);
}
