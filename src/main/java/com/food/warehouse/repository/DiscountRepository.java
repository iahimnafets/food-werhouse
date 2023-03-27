package com.food.warehouse.repository;

import com.food.warehouse.entities.Discount;
import com.food.warehouse.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

}
