package com.food.warehouse.entities;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @SequenceGenerator(
            name = "discount_sequence",
            sequenceName = "discount_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "discount_sequence"
    )
    @Column(name = "discount_id")
    private Long id;

    @Column(name = "discount_name", nullable = false)
    private String discountName;

    @Column(name = "discount_percentage", nullable = false)
    private BigDecimal discountPercentage;

    @Column(name = "discount_start_date", nullable = false)
    private Date discountStartDate;

    @Column(name = "discount_end_date", nullable = false)
    private Date discountEndDate;

}
