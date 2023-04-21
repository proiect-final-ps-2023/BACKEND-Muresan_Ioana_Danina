package com.proiectps.shopping.repository;

import com.proiectps.shopping.model.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review,Long> {
    List<Review> findAllByPerfumeId(Long perfumeId);
}
