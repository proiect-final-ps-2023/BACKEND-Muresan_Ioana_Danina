package com.proiectps.shopping.service.impl;

import com.proiectps.shopping.dto.ReviewDTO;
import com.proiectps.shopping.mapper.PerfumeMapper;
import com.proiectps.shopping.mapper.ReviewMapper;
import com.proiectps.shopping.model.Review;
import com.proiectps.shopping.repository.PerfumeRepository;
import com.proiectps.shopping.repository.ReviewRepository;
import com.proiectps.shopping.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Override
    public Review createReview(Long perfumeId, Review review) {
      return perfumeRepository.findById(perfumeId).map(perfume -> {
            review.setPerfume(perfume);
            perfume.getReviews().add(review);
            return reviewRepository.save(review);
        }).orElseThrow(() -> new EntityNotFoundException("Perfume with id " + perfumeId + " not found"));

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

    public List<ReviewDTO> getAllReviewsByPerfumeId(Long perfumeId) {
           return perfumeRepository.findById(perfumeId).map(perfume -> perfume.getReviews().stream()
                   .map(ReviewMapper::mapModelToDTO)
                   .collect(Collectors.toList())).orElseThrow(() -> new EntityNotFoundException("Perfume with id " + perfumeId + " not found"));
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

    public List<Review> getAllReviews() {
        return (List<Review>) reviewRepository.findAll();
    }
}
