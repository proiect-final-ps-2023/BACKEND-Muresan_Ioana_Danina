package com.proiectps.shopping.dto;

import com.proiectps.shopping.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDTO {
    private Long idPerfume;
    private Integer quantity;
    private Long  userId;
}
