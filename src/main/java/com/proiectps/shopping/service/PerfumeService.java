package com.proiectps.shopping.service;

import com.proiectps.shopping.dto.PerfumeDTO;
import com.proiectps.shopping.model.Perfume;

import java.util.List;

public interface PerfumeService {
    Perfume getPerfumeById(Long perfumeId);
    List<PerfumeDTO> findByBrand(String brand);
    List<PerfumeDTO> findByGender(String gender);
    String deletePerfume(Long perfumeId);
    Perfume updatePrice(Long perfumeId, Integer price);
    List<PerfumeDTO> findAllByTitle(String title);
    Perfume savePerfume(Perfume perfume);
    List<PerfumeDTO> findAll();
}
