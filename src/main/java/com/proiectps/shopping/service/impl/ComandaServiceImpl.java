package com.proiectps.shopping.service.impl;

import com.proiectps.shopping.dto.ComandaDTO;
import com.proiectps.shopping.dto.OrderItemDTO;
import com.proiectps.shopping.mapper.ComandaMapper;
import com.proiectps.shopping.mapper.OrderItemMapper;
import com.proiectps.shopping.mapper.PerfumeMapper;
import com.proiectps.shopping.model.Comanda;
import com.proiectps.shopping.model.OrderItem;
import com.proiectps.shopping.model.Perfume;
import com.proiectps.shopping.repository.OrderItemRepository;
import com.proiectps.shopping.repository.OrderRepository;
import com.proiectps.shopping.repository.PerfumeRepository;
import com.proiectps.shopping.repository.UserRepository;
import com.proiectps.shopping.service.ComandaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ComandaServiceImpl implements ComandaService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;
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
    {   List<OrderItem> orderItems = (List<OrderItem>) orderItemRepository.findAllById(Collections.singleton(orderId));
        for(OrderItem orderItem : orderItems)
        {
            orderItemRepository.delete(orderItem);
        }
        Comanda comanda = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + orderId));
        orderRepository.delete(comanda);
        return "Order deleted";
    }


    @Override
    public Comanda createOrder(Comanda comanda) {
            return orderRepository.save(comanda);
    }

    @Override
    public List<ComandaDTO> getAllOrders() {
        List<Comanda> list= (List<Comanda>) orderRepository.findAll();
        return list.stream()
                .map(ComandaMapper::mapModelToDTO)
                .collect(Collectors.toList());
    }
    public Object getComenziByUserId(Long id) {
        return orderRepository.findComenziByUserId(id);
    }

    public ComandaDTO postOrder(String phone,String city,String address, Map<Long, Long> perfumesId, Long userId) {
        List<OrderItem> orderItemList = new ArrayList<>();
        long totalPrice = 0;
        for (Map.Entry<Long, Long> entry : perfumesId.entrySet()) {
            Perfume perfume = perfumeRepository.findById(entry.getKey()).get();
            if(perfume.getNew_price()!=null)
                totalPrice += perfume.getNew_price() * entry.getValue();
            else
                totalPrice += perfume.getPrice() * entry.getValue();

            OrderItem orderItem = new OrderItem();
            orderItem.setPerfume(perfume);
            orderItem.setUser(userRepository.findUserById(userId));
            if(perfume.getStock()-entry.getValue()<0)
                throw new IllegalArgumentException("Not enough stock for perfume: " + perfume.getTitle());
            else orderItem.setQuantity(Math.toIntExact(entry.getValue()));
            perfume.setStock((int) (perfume.getStock()-entry.getValue()));
            perfumeRepository.save(perfume);
            orderItemList.add(orderItem);
            orderItemRepository.save(orderItem);
        }
        Comanda comanda = new Comanda();
        comanda.setPhone_number(phone);
        comanda.setAddress(address);
        comanda.setCity(city);
        comanda.setTotal_price((int) totalPrice);
        comanda.getOrderItems().addAll(orderItemList);
        comanda.setUser(userRepository.findUserById(userId));
        orderRepository.save(comanda);
        return ComandaMapper.mapModelToDTO(comanda);
    }



}
