package com.proiectps.shopping.service;

import com.proiectps.shopping.model.Comanda;
import com.proiectps.shopping.model.OrderItem;
import com.proiectps.shopping.model.User;
import com.proiectps.shopping.repository.OrderRepository;
import com.proiectps.shopping.repository.UserRepository;
import com.proiectps.shopping.service.impl.ComandaServiceImpl;
import com.proiectps.shopping.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComandaServiceTest {
    @Mock
    private OrderRepository orderRepository= mock(OrderRepository.class);

    @InjectMocks
    private ComandaServiceImpl comandaService= new ComandaServiceImpl(orderRepository);

    private Comanda comanda;
    private User user;

    @BeforeEach
    public void setUp() {

        user = new User(1L,"username", "password", "email", true);
        comanda = new Comanda(100,"Bucuresti", "Strada", "123456789", LocalDate.now(), user, new ArrayList<>());
        orderRepository.save(comanda);
    }

    @Test
    public void testGetOrderById() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(comanda));
        Optional<Comanda> result = comandaService.getOrderById(1L);
        assertEquals(result.get(), comanda);
    }


    @Test
    public void testDeleteOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(comanda));
        String result = comandaService.deleteOrder(1L);
        assertEquals(result, "Order deleted successfully");
    }

    @Test
    public void testDeleteOrderNotAdmin() {
        user.setIsAdmin(false);
        comanda.setUser(user);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(comanda));
        String result = comandaService.deleteOrder(1L);
        assertEquals(result, "You are not an admin");
    }

    @Test
    public void testCreateOrder() {
        when(orderRepository.save(comanda)).thenReturn(comanda);
        Comanda result = comandaService.createOrder(comanda);
        assertEquals(result, comanda);
    }

    @Test
    public void testGetAllOrders() {
        List<Comanda> orders = new ArrayList<>();
        orders.add(comanda);
        when(orderRepository.findAll()).thenReturn(orders);
        List<Comanda> result = comandaService.getAllOrders();
        assertEquals(result, orders);
    }
}
