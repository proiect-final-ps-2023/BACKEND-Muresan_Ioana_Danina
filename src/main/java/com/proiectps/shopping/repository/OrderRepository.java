package com.proiectps.shopping.repository;

import com.proiectps.shopping.model.Comanda;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Comanda,Long> {

}
