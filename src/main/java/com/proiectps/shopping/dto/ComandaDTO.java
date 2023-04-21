package com.proiectps.shopping.dto;

import com.proiectps.shopping.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComandaDTO {

    private Long id;
    private Integer total_price;
    private LocalDate date;
    private String address;
    private String city;
    private Long user_id;
    private String phone_number;
//    private List<OrderItem> orderItems;
}
