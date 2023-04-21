package com.proiectps.shopping.service;

import com.proiectps.shopping.dto.ComandaDTO;
import com.proiectps.shopping.model.Comanda;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ComandaService {
    Optional<Comanda> getOrderById(Long orderId);
    String deleteOrder(Long orderId);
    Comanda createOrder(Comanda comanda);
    List<ComandaDTO> getAllOrders();

}
