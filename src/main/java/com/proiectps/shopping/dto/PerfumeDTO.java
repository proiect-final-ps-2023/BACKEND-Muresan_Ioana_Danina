package com.proiectps.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PerfumeDTO {

    private Long id;
    private String title;
    private Integer price;
    private Integer new_price;
    private Integer volume;
    private String gender;
    private String brand;
    private String fragrance_top_notes;
    private String fragrance_middle_notes;
    private String fragrance_base_notes;
    private String description;
    private Integer stock;
    private String image;

}
