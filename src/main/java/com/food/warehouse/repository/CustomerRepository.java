package com.food.warehouse.repository;

import com.food.warehouse.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByCustomerNameContaining(String customerName);

    List<Customer> findByEmail(String email);

}
