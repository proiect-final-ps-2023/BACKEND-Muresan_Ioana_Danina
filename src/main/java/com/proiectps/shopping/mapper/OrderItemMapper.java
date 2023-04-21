package com.proiectps.shopping.mapper;

import com.proiectps.shopping.controller.PerfumeController;
import com.proiectps.shopping.dto.OrderItemDTO;
import com.proiectps.shopping.model.OrderItem;
import com.proiectps.shopping.repository.PerfumeRepository;
import com.proiectps.shopping.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemMapper {

    @Autowired
    private static PerfumeRepository perfumeRepository;

    @Autowired
    private static UserRepository userRepository;

    public static OrderItemDTO mapModelToDTO(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setIdPerfume(orderItem.getPerfume().getId());
        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setUserId(orderItem.getUser().getId());
        return orderItemDTO;
    }

    public static OrderItem mapDTOToModel(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setPerfume(perfumeRepository.findById(orderItemDTO.getIdPerfume()).get());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setUser(userRepository.findUserById(orderItemDTO.getUserId()));
        return orderItem;
    }

}
