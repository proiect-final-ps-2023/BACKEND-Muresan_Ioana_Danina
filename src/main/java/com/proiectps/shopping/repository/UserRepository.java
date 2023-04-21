package com.proiectps.shopping.repository;


import com.proiectps.shopping.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findUserById(Long id);
    Optional<User> findByUsername(String username);

    Optional<Object> findByEmail(String email);
}
