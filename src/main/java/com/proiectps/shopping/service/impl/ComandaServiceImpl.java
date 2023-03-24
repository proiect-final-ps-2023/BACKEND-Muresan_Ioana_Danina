package com.proiectps.shopping.service.impl;

import com.proiectps.shopping.model.Comanda;
import com.proiectps.shopping.repository.OrderRepository;
import com.proiectps.shopping.service.ComandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComandaServiceImpl implements ComandaService {

    @Autowired
    private OrderRepository orderRepository;
    public ComandaServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Comanda> getOrderById(Long orderId) {
        Comanda comanda = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + orderId));
        return orderRepository.findById(orderId);

    }

    @Override
    public String deleteOrder(Long orderId)
    {
        Comanda comanda = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + orderId));
        if(comanda.getUser().getIsAdmin()) {
            orderRepository.delete(comanda);
            return "Order deleted successfully";
        }
        else
            return "You are not an admin";
    }

    @Override
    public Comanda createOrder(Comanda comanda) {
            return orderRepository.save(comanda);
    }

    @Override
    public List<Comanda> getAllOrders() {
        return (List<Comanda>) orderRepository.findAll();
    }
}
