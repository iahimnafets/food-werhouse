package com.food.warehouse.service;

import com.food.warehouse.dto.product.ProductCustomJpaResult;
import com.food.warehouse.dto.product.ProductCustomResponse;
import com.food.warehouse.dto.product.ProductDiscountResponse;
import com.food.warehouse.dto.product.ProductResponse;
import com.food.warehouse.entities.Product;
import com.food.warehouse.exception.ApiRequestException;
import com.food.warehouse.repository.ProductRepository;
import com.food.warehouse.utilities.WarehouseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    @Autowired
    private  ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void saveAll(List<Product> listProduct ){
        log.info("saveAll-RUN");
        productRepository.saveAll(listProduct);
    }

    public ProductResponse getProductById(Long productId) {
        log.info("getProductById-RUN productId: {}", productId );
        if(productId == null){
            throw new ApiRequestException("Product Id is mandatory for the method: getProductById() ");
        }
        Product product = productRepository.findById( productId ).orElseThrow( () ->  new ApiRequestException("Product is not present in Data base") );
        return modelMapper.map(product, ProductResponse.class);
    }

    public List<ProductResponse>  getAllProducts() {
        log.info("getAllProducts-RUN " );
        List<ProductResponse> result  = new ArrayList<>();
        List<Product> listProduct =  productRepository.findAll();

        listProduct.stream().forEach((product) -> {
            result.add( modelMapper.map(product, ProductResponse.class) );
        });
        return result;
    }

    public List<ProductCustomResponse> getWeightOfAllFoodItems() {
        log.info("getWeightOfAllFoodItems-RUN " );
        List<ProductCustomResponse> result = new ArrayList<>();

        List<ProductCustomJpaResult> productCustomJpa = productRepository.getGetWeightOfAllFoodItems();
        buildProductCustomResponse(result, productCustomJpa);
        return result;
    }

    public List<ProductCustomResponse> getWeightOfAllFoodItemsByCategory() {
        log.info("getWeightOfAllFoodItemsByCategory-RUN " );
        List<ProductCustomResponse> result = new ArrayList<>();

        List<ProductCustomJpaResult> productCustomJpa = productRepository.getWeightOfAllFoodItemsByCategory();
        buildProductCustomResponse(result, productCustomJpa);
        return result;
    }

    public List<ProductCustomResponse> getWeightOfAllVegetables() {
        log.info("getWeightOfAllVegetables-RUN " );
        List<ProductCustomResponse> result = new ArrayList<>();

        List<ProductCustomJpaResult> productCustomJpa = productRepository.getWeightOfAllVegetables();
        buildProductCustomResponse(result, productCustomJpa);
        return result;
    }


    public List<ProductResponse> getProductsVegetables() {
        log.info("getProductsVegetables-RUN " );
        List<ProductResponse> result = new ArrayList<>();

        List<Product> listResult = productRepository.getProductsVegetables();
        listResult.stream().forEach((product) -> {
            result.add( modelMapper.map(product, ProductResponse.class) );
        });
        return result;
    }

    public List<ProductDiscountResponse>  getProductsWithDiscount() {
        log.info("getProductsWithDiscount-RUN " );
        List<ProductDiscountResponse> result  = new ArrayList<>();

        List<Product> listProductWithDiscount =  productRepository.getProductsWithDiscount();
        listProductWithDiscount.stream().forEach((product) -> {
            result.add( modelMapper.map(product, ProductDiscountResponse.class) );
        });

        result.stream().forEach((prod) -> {
            if (prod.getDiscount() != null) {

                BigDecimal amountDiscount = WarehouseUtils.getDiscountedPrice(
                                prod.getUnitPrice(),
                                prod.getDiscount().getDiscountPercentage(),
                                prod.getDiscount().getDiscountStartDate(),
                                prod.getDiscount().getDiscountEndDate()
                );
                if(amountDiscount.compareTo( BigDecimal.ZERO ) == 0 ){
                    prod.setMsg(" Discount present but it begins " + prod.getDiscount().getDiscountStartDate() +" and ends: " + prod.getDiscount().getDiscountEndDate() );
                }
                prod.setDiscountedPrice(amountDiscount);
            }
        });
        return result;
    }




    private void buildProductCustomResponse(List<ProductCustomResponse> result, List<ProductCustomJpaResult> productCustomJpa) {
        if(productCustomJpa != null ) {
            productCustomJpa.stream().forEach((res) -> {
                result.add( new ProductCustomResponse(res.getCategoryId(), res.getTotalQuantity()));
            });
        }
    }
}
