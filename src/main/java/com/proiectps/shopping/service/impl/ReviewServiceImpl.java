package com.proiectps.shopping.service.impl;

import com.proiectps.shopping.model.Review;
import com.proiectps.shopping.repository.ReviewRepository;
import com.proiectps.shopping.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review getReviewById(Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            return optionalReview.get();
        } else {
            throw new EntityNotFoundException("Review with id " + id + " not found");
        }
    }

    @Override
    public List<Review> getAllReviews() {
        return (List<Review>) reviewRepository.findAll();
    }

    @Override
    public Review updateReview(Long id, Review review) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review existingReview = optionalReview.get();
            existingReview.setAuthor(review.getAuthor());
            existingReview.setMessage(review.getMessage());
            existingReview.setRating(review.getRating());
            return reviewRepository.save(existingReview);
        } else {
            throw new EntityNotFoundException("Review with id " + id + " not found");
        }
    }

    @Override
    public String deleteReview(Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            reviewRepository.deleteById(id);
            return "Review deleted successfully";
        } else {
            throw new EntityNotFoundException("Review with id " + id + " not found");
        }
    }
}
