package com.food.warehouse.dto.product;

import com.food.warehouse.entities.Category;
import com.food.warehouse.entities.Discount;
import com.food.warehouse.entities.Supplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDiscountResponse {

    private Long id;
    private String productName;
    private BigDecimal unitPrice;
    private Discount discount;
    private BigDecimal discountedPrice;
    private String msg;
}
