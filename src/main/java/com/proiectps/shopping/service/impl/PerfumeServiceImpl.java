package com.proiectps.shopping.service.impl;

import com.proiectps.shopping.dto.PerfumeDTO;
import com.proiectps.shopping.repository.PerfumeRepository;
import com.proiectps.shopping.service.PerfumeService;
import com.proiectps.shopping.model.Perfume;
import com.proiectps.shopping.mapper.PerfumeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<PerfumeDTO> findByGender(String gender) {
        return perfumeRepository.findAllByGender(gender).stream()
                .map(PerfumeMapper::mapModelToDTO)
                .collect(Collectors.toList());
    }
    public List<PerfumeDTO> findByBrand(String brand) {
        return perfumeRepository.findAllByBrand(brand).stream()
                .map(PerfumeMapper::mapModelToDTO)
                .collect(Collectors.toList());
    }

    public List<PerfumeDTO> findAllByTitle(String title) {
        return perfumeRepository.findAllByTitle(title).stream()
                .map(PerfumeMapper::mapModelToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public Perfume savePerfume(Perfume perfume) {
        return perfumeRepository.save(perfume);
    }

    @Override
    public List<PerfumeDTO> findAll() {
        List<Perfume> perfumes = (List<Perfume>) perfumeRepository.findAll();
        return perfumes.stream()
                .map(PerfumeMapper::mapModelToDTO)
                .collect(Collectors.toList());
    }

    public Perfume updatePerfume(Long id, Perfume perfume) {
        Perfume updatePerfume = perfumeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid perfume Id:" + id));
        updatePerfume.setTitle(perfume.getTitle());
        updatePerfume.setBrand(perfume.getBrand());
        updatePerfume.setGender(perfume.getGender());
        updatePerfume.setFragrance_top_notes(perfume.getFragrance_top_notes());
        updatePerfume.setFragrance_middle_notes(perfume.getFragrance_middle_notes());
        updatePerfume.setFragrance_base_notes(perfume.getFragrance_base_notes());
        updatePerfume.setDescription(perfume.getDescription());
        updatePerfume.setPrice(perfume.getPrice());
        updatePerfume.setVolume(perfume.getVolume());
        updatePerfume.setStock(perfume.getStock());
        perfumeRepository.save(updatePerfume);
        return updatePerfume;
    }

    public List<String> findAllBrands() {
        List<String> brands = new ArrayList<>();
        List<Perfume> perfumes = (List<Perfume>) perfumeRepository.findAll();
        for (Perfume perfume : perfumes) {
            String brand = perfume.getBrand();
            if (!brands.contains(brand)) {
                brands.add(brand);
            }
        }
        return brands;
    }

    public List<PerfumeDTO> findByPriceBetween(Integer min, Integer max) {
        List<Perfume> perfumes = (List<Perfume>) perfumeRepository.findAll();
        List<Perfume> perfumesBetween = new ArrayList<>();
        for (Perfume perfume : perfumes) {
            if(perfume.getNew_price()==null)
            {
                if (perfume.getPrice() > min && perfume.getPrice() <= max) {
                    perfumesBetween.add(perfume);
                }
            }
            else
            {
                if (perfume.getNew_price() > min && perfume.getNew_price() <= max) {
                    perfumesBetween.add(perfume);
                }
            }
        }
        return perfumesBetween.stream()
                .map(PerfumeMapper::mapModelToDTO)
                .collect(Collectors.toList());
    }

    public List<PerfumeDTO> perfumesOrderAsc() {
        List<Perfume> perfumes = (List<Perfume>) perfumeRepository.findAll();
        List<Perfume> perfumesOrderAsc;
        perfumesOrderAsc = perfumes.stream()
                .sorted(Comparator.comparing(perfume -> perfume.getNew_price() != null ? perfume.getNew_price() : perfume.getPrice()))
                .collect(Collectors.toList());
        return perfumesOrderAsc.stream()
                .map(PerfumeMapper::mapModelToDTO)
                .collect(Collectors.toList());
    }

    public List<PerfumeDTO> perfumesOrderDesc() {
        List<Perfume> perfumes = (List<Perfume>) perfumeRepository.findAll();
        List<Perfume> perfumesOrderDesc;
        perfumesOrderDesc = perfumes.stream()
                .sorted(Comparator.comparing(perfume -> perfume.getNew_price() != null ? perfume.getNew_price() : perfume.getPrice(), Comparator.reverseOrder()))
                .collect(Collectors.toList());
        return perfumesOrderDesc.stream()
                .map(PerfumeMapper::mapModelToDTO)
                .collect(Collectors.toList());
    }

}

