package com.proiectps.shopping.service;

import com.proiectps.shopping.model.Review;
import com.proiectps.shopping.repository.ReviewRepository;
import com.proiectps.shopping.service.impl.ReviewServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class ReviewServiceTest {
    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /*
    @Test
    void createReview() {
        // Arrange
        Review review = new Review("John Doe", "Great product!", LocalDate.of(2021, 1, 1), 5);
        when(reviewRepository.save(review)).thenReturn(review);

        // Act
        Review createdReview = reviewService.createReview(review);

        // Assert
        assertNotNull(createdReview);
        assertEquals("John Doe", createdReview.getAuthor());
        assertEquals("Great product!", createdReview.getMessage());
        assertEquals(LocalDate.of(2021, 1, 1), createdReview.getDate());
        assertEquals(5, createdReview.getRating());
        assertEquals(review, createdReview);

        verify(reviewRepository).save(review);
    }

     */

    @Test
    void getReviewById() {
        Review review = new Review("John Doe", "Great product!", LocalDate.of(2021, 1, 1), 5);
        Optional<Review> optionalReview = Optional.of(review);

        when(reviewRepository.findById(1L)).thenReturn(optionalReview);

        Review foundReview = reviewService.getReviewById(1L);

        assertEquals(review, foundReview);

        assertThrows(EntityNotFoundException.class, () -> reviewService.getReviewById(2L));
    }

    @Test
    void getAllReviews() {
        Review review1 = new Review("John Doe", "Great product!", LocalDate.of(2021, 1, 1), 5);
        Review review2 = new Review("Jane Doe", "Not so great product.", LocalDate.of(2021, 2, 1), 2);

        List<Review> reviewList = new ArrayList<>();
        reviewList.add(review1);
        reviewList.add(review2);

        when(reviewRepository.findAll()).thenReturn(reviewList);

        List<Review> foundReviews = reviewService.getAllReviews();

        assertEquals(reviewList, foundReviews);
    }

    @Test
    void updateReview() {
        // Create an initial review
        Review initialReview = new Review("John Doe", "Great product!", LocalDate.of(2021, 1, 1), 5);
        when(reviewRepository.findById(initialReview.getId())).thenReturn(Optional.of(initialReview));

        // Create an updated review
        Review updatedReview = new Review("Jane Smith", "Terrible product!", LocalDate.of(2022, 2, 2), 1);
        when(reviewRepository.save(initialReview)).thenReturn(updatedReview);

        // Call the updateReview method
        Review resultReview = reviewService.updateReview(initialReview.getId(), updatedReview);

        // Check that the result is the updated review
        assertEquals(updatedReview.getId(), resultReview.getId());
        assertEquals(updatedReview.getAuthor(), resultReview.getAuthor());
        assertEquals(updatedReview.getMessage(), resultReview.getMessage());
        assertEquals(updatedReview.getDate(), resultReview.getDate());
        assertEquals(updatedReview.getRating(), resultReview.getRating());
    }

    @Test
    void updateReviewNotFound() {
        // Create a review that does not exist in the repository
        Review review = new Review("John Doe", "Great product!", LocalDate.of(2021, 1, 1), 5);
        when(reviewRepository.findById(review.getId())).thenReturn(Optional.empty());

        // Call the updateReview method and expect an EntityNotFoundException to be thrown
        assertThrows(EntityNotFoundException.class, () -> reviewService.updateReview(review.getId(), review));
    }

    @Test
    void deleteReview() {
        Review review = new Review("John Doe", "Great product!", LocalDate.of(2021, 1, 1), 5);
        Optional<Review> optionalReview = Optional.of(review);

        when(reviewRepository.findById(1L)).thenReturn(optionalReview);

        String result = reviewService.deleteReview(1L);

        assertEquals("Review deleted successfully", result);

        verify(reviewRepository).deleteById(1L);
    }

    @Test
    void deleteReviewNotFound() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> reviewService.deleteReview(1L));
    }

}
