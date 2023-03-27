package com.food.warehouse.repository;

import com.food.warehouse.entities.Category;
import com.food.warehouse.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCategory(Category category);

    List<Order> findByProductPrice(BigDecimal productPrice);


}
