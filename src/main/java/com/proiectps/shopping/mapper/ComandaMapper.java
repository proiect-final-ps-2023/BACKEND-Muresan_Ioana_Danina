package com.proiectps.shopping.mapper;

import com.proiectps.shopping.dto.ComandaDTO;
import com.proiectps.shopping.model.Comanda;
import org.springframework.stereotype.Component;

@Component
public class ComandaMapper {

    public static ComandaDTO mapModelToDTO(Comanda comanda) {
        ComandaDTO comandaDTO = new ComandaDTO();
        comandaDTO.setId(comanda.getId());
        comandaDTO.setTotal_price(comanda.getTotal_price());
        comandaDTO.setDate(comanda.getDate());
        comandaDTO.setAddress(comanda.getAddress());
        comandaDTO.setCity(comanda.getCity());
        if(comanda.getUser() != null)
        comandaDTO.setUser_id(comanda.getUser().getId());
        comandaDTO.setPhone_number(comanda.getPhone_number());
        return comandaDTO;
    }

    public Comanda mapDTOToModel(ComandaDTO comandaDTO) {
        Comanda comanda = new Comanda();
        comanda.setId(comandaDTO.getId());
        comanda.setTotal_price(comandaDTO.getTotal_price());
        comanda.setDate(comandaDTO.getDate());
        comanda.setAddress(comandaDTO.getAddress());
        return comanda;
    }
}
