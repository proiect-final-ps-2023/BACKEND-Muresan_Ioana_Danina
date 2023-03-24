package com.proiectps.shopping.service;

import com.proiectps.shopping.model.Perfume;

import java.util.List;

public interface PerfumeService {
    Perfume getPerfumeById(Long perfumeId);
    List<Perfume> findByBrand(String brand);
    List<Perfume> findByGender(String gender);
    String deletePerfume(Long perfumeId);
    Perfume updatePrice(Long perfumeId, Integer price);
    Perfume savePerfume(Perfume perfume);

}
