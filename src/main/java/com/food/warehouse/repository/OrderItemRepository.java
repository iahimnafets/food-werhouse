package com.food.warehouse.repository;

import com.food.warehouse.entities.OrderItem;
import com.food.warehouse.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
