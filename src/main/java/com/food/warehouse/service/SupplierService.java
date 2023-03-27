package com.food.warehouse.service;


import com.food.warehouse.entities.Supplier;
import com.food.warehouse.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupplierService {

   private final SupplierRepository supplierRepository;

   public void save(Supplier supplier ){
       supplierRepository.save( supplier );
   }

}
