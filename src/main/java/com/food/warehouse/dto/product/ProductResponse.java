package com.food.warehouse.dto.product;

import com.food.warehouse.entities.Category;
import com.food.warehouse.entities.Discount;
import com.food.warehouse.entities.Supplier;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long id;
    private String productName;
    private String productDescription;
    private Category category;
    private BigDecimal unitPrice;
    private String unitOfMeasure;
    private Integer quantityOnHand;
    private Supplier supplier;
    private Discount discount;
}
