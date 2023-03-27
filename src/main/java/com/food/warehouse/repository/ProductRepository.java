package com.food.warehouse.repository;

import com.food.warehouse.dto.product.ProductCustomJpaResult;
import com.food.warehouse.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
    

    @Query(value =  "SELECT  sum(u.quantity_on_hand) as totalQuantity FROM products u" , nativeQuery = true)
    List<ProductCustomJpaResult> getGetWeightOfAllFoodItems();

    @Query(value = "SELECT sum(u.quantity_on_hand) as totalQuantity " +
            " FROM products u " +
            " where " +
            " u.category_id=2 " ,
            nativeQuery = true)
    List<ProductCustomJpaResult> getWeightOfAllVegetables();

    // This was NOT required, but I enjoyed doing it
    @Query(value =  "SELECT u.category_id as categoryId,  sum(u.quantity_on_hand) as totalQuantity FROM products u GROUP BY u.category_id" ,
            nativeQuery = true)
    List<ProductCustomJpaResult> getWeightOfAllFoodItemsByCategory();


    @Query(value =  "SELECT u.* FROM products u Where u.category_id=2 " ,
            nativeQuery = true)
    List<Product> getProductsVegetables();


    @Query(value =  "SELECT u.* FROM products u" +
            "  Where u.discount_id is not null" ,
            nativeQuery = true)
    List<Product> getProductsWithDiscount();
}

