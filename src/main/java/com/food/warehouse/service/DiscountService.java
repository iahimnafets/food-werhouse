package com.food.warehouse.service;

import com.food.warehouse.entities.Discount;
import com.food.warehouse.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    public void saveAll(List<Discount> listDiscounts ){
        log.info("saveAll-RUN");
        discountRepository.saveAll(listDiscounts);

    }
}
