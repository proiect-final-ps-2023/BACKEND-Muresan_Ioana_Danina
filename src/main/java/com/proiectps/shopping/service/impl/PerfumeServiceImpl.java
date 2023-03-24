package com.proiectps.shopping.service.impl;

import com.proiectps.shopping.repository.PerfumeRepository;
import com.proiectps.shopping.service.PerfumeService;
import com.proiectps.shopping.model.Perfume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfumeServiceImpl implements PerfumeService {

    @Autowired
    private PerfumeRepository perfumeRepository;
    public PerfumeServiceImpl(PerfumeRepository perfumeRepository) {
        this.perfumeRepository = perfumeRepository;
    }

    @Override
    public Perfume getPerfumeById(Long perfumeId) {
       return perfumeRepository.findById(perfumeId).orElseThrow(() -> new IllegalArgumentException("Invalid perfume Id:" + perfumeId));
    }

    @Override
    public String deletePerfume(Long perfumeId) {

        Perfume perfume = perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid perfume Id:" + perfumeId));
        perfumeRepository.delete(perfume);
        return "Perfume deleted successfully";
    }

    @Override
    public Perfume updatePrice(Long perfumeId, Integer price)
    {
        Perfume updatePerfume = perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid perfume Id:" + perfumeId));
        updatePerfume.setPrice(price);
        perfumeRepository.save(updatePerfume);
        return updatePerfume;
    }

    public List<Perfume> findByGender(String gender) {
        return (List<Perfume>) perfumeRepository.findFirstByGender(gender);
    }
    public List<Perfume> findByBrand(String brand) {
        return (List<Perfume>) perfumeRepository.findFirstByBrand(brand);
    }

    @Override
    public Perfume savePerfume(Perfume perfume) {
        return perfumeRepository.save(perfume);
    }
}

