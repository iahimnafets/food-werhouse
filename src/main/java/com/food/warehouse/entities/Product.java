package com.food.warehouse.entities;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "product_sequence"
    )
    @Column(name = "products_id", nullable = false)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "unit_of_measure")
    private String unitOfMeasure;

    @Column(name = "quantity_on_hand")
    private Integer quantityOnHand;

    @ManyToOne()
    @JoinColumn(name = "supplier_id" )
    private Supplier supplier;

    @ManyToOne()
    @JoinColumn(name = "discount_id" )
    private Discount discount;

}


