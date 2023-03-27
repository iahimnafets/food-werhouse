package com.food.warehouse.entities;


import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @SequenceGenerator(
            name = "supplier_sequence",
            sequenceName = "supplier_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "supplier_sequence"
    )
    @Column(name = "supplier_id")
    private Long id;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "supplier_contact_info")
    private String supplierContactInfo;

    @Column(name = "supplier_address")
    private String supplierAddress;

    // @OneToMany(mappedBy = "supplier" , fetch = FetchType.LAZY )
   // private List<Product> products;

}

