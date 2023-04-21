package com.proiectps.shopping.repository;

import com.proiectps.shopping.dto.PerfumeDTO;
import com.proiectps.shopping.model.Perfume;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PerfumeRepository extends CrudRepository<Perfume,Long> {

    List<Perfume> findFirstByGender(String gender);
    List<Perfume> findAllByBrand(String brand);
    List<Perfume> findAllByGender(String gender);
    List<Perfume> findAllByTitle(String title);
}
