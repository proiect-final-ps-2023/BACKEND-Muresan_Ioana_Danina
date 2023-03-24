package com.proiectps.shopping.repository;

import com.proiectps.shopping.model.OrderItem;
import com.proiectps.shopping.model.User;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem,Long> {
}
