package com.proiectps.shopping.mapper;

import com.proiectps.shopping.dto.PerfumeDTO;
import com.proiectps.shopping.model.Perfume;
import org.springframework.stereotype.Component;

@Component
public class PerfumeMapper {

        public static PerfumeDTO mapModelToDTO(Perfume perfume) {
            PerfumeDTO perfumeDTO = new PerfumeDTO();
            perfumeDTO.setId(perfume.getId());
            perfumeDTO.setTitle(perfume.getTitle());
            perfumeDTO.setPrice(perfume.getPrice());
            perfumeDTO.setVolume(perfume.getVolume());
            perfumeDTO.setNew_price(perfume.getNew_price());
            perfumeDTO.setGender(perfume.getGender());
            perfumeDTO.setBrand(perfume.getBrand());
            perfumeDTO.setFragrance_top_notes(perfume.getFragrance_top_notes());
            perfumeDTO.setFragrance_middle_notes(perfume.getFragrance_middle_notes());
            perfumeDTO.setFragrance_base_notes(perfume.getFragrance_base_notes());
            perfumeDTO.setDescription(perfume.getDescription());
            perfumeDTO.setStock(perfume.getStock());
            perfumeDTO.setImage(perfume.getImage());

            return perfumeDTO;
        }

}
