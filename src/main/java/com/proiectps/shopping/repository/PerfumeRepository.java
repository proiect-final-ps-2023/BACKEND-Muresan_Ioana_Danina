package com.proiectps.shopping.repository;

import com.proiectps.shopping.model.Perfume;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfumeRepository extends CrudRepository<Perfume,Long> {

    List<Perfume> findFirstByBrand(String brand);
    List<Perfume> findFirstByGender(String gender);
}
