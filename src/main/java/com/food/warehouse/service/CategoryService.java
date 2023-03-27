package com.food.warehouse.service;

import com.food.warehouse.entities.Category;
import com.food.warehouse.repository.CategoryRepository;
import com.food.warehouse.repository.OrderRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void saveAll(List<Category> listCategory ){
        categoryRepository.saveAll(listCategory);
    }

}
