package com.proiectps.shopping.mapper;

import com.proiectps.shopping.dto.ReviewDTO;
import com.proiectps.shopping.model.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

        public static ReviewDTO mapModelToDTO(Review review) {
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setId(review.getId());
            reviewDTO.setAuthor(review.getAuthor());
            reviewDTO.setMessage(review.getMessage());
            reviewDTO.setDate(review.getDate());
            reviewDTO.setRating(review.getRating());
            reviewDTO.setPerfumeId(review.getPerfume().getId());
            return reviewDTO;
        }


}
