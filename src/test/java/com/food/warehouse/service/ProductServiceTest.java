package com.food.warehouse.service;

import com.food.warehouse.dto.product.ProductCustomJpaResult;
import com.food.warehouse.dto.product.ProductCustomResponse;
import com.food.warehouse.dto.product.ProductResponse;
import com.food.warehouse.entities.Category;
import com.food.warehouse.entities.Discount;
import com.food.warehouse.entities.Product;
import com.food.warehouse.entities.Supplier;
import com.food.warehouse.exception.ApiRequestException;
import com.food.warehouse.repository.ProductRepository;
import com.food.warehouse.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith( MockitoJUnitRunner.class )
@Slf4j
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductService productService;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProductById() {

        Long productId = 1L;
        Product product = Product.builder()
        .id(productId)
        .productName("Test Product")
        .unitPrice (BigDecimal.valueOf(10.0))
        .quantityOnHand(200)
                .build();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        ProductResponse productResponse =  ProductResponse.builder()
                .id(productId)
                .productName("Test Product")
                .unitPrice(BigDecimal.valueOf(10.0))
                .quantityOnHand(20)
                        .build();

        when(modelMapper.map(product, ProductResponse.class)).thenReturn(productResponse);
        ProductResponse actualProductResponse = productService.getProductById(productId);

        assertEquals(productResponse, actualProductResponse);
        verify(productRepository, times(1)).findById(productId);
        verify(modelMapper, times(1)).map(product, ProductResponse.class);
    }

    @Test(expected = ApiRequestException.class)
    public void getProductById_NullIdTest() {
        Long productId = null;
        productService.getProductById(productId);
    }

    @Test(expected = ApiRequestException.class)
    public void getProductById_NotFoundTest() {
        Long productId = 3L;
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty());
        productService.getProductById(productId);
    }


    @Test
    public void getAllProducts_ListOfProductResponses() {

        Product product1 = Product.builder()
                .productName("strawberries")
                .productDescription("Fresh and delicious strawberries")
                .category(new Category(3L, null, null))
                .unitPrice(new BigDecimal("1.99"))
                .unitOfMeasure("kg")
                .quantityOnHand(150)
                .supplier( new Supplier(1L , null , null , null))
                .build();

        Product product2 = Product.builder()
                .productName("cherries")
                .productDescription("Fresh and delicious cherries")
                .category(new Category(3L, null, null))
                .unitPrice(new BigDecimal("1.49"))
                .unitOfMeasure("kg")
                .quantityOnHand(200)
                .supplier( new Supplier(1L , null , null , null))
                .discount( new Discount(2L, null, null, null, null ) )
                .build();


        List<Product> products = Arrays.asList(product1, product2);
        List<ProductResponse> productResponses = Arrays.asList(new ProductResponse(), new ProductResponse());
        when(productRepository.findAll()).thenReturn(products);
        when(modelMapper.map(product1, ProductResponse.class)).thenReturn(productResponses.get(0));
        when(modelMapper.map(product2, ProductResponse.class)).thenReturn(productResponses.get(1));

        List<ProductResponse> result = productService.getAllProducts();

        assertEquals(productResponses, result);
        verify(productRepository).findAll();
        verify(modelMapper).map(product1, ProductResponse.class);
        verify(modelMapper).map(product2, ProductResponse.class);
    }


    @Test
    public void testGetWeightOfAllFoodItems() {
        List<ProductCustomJpaResult> jpaResultList = new ArrayList<>();
        jpaResultList.add(new ProductCustomJpaResult() {
            @Override
            public Integer getCategoryId() {
                return 1;
            }

            @Override
            public Integer getTotalQuantity() {
                return 50;
            }
        });
        jpaResultList.add(new ProductCustomJpaResult() {
            @Override
            public Integer getCategoryId() {
                return 2;
            }

            @Override
            public Integer getTotalQuantity() {
                return 10;
            }
        });
        when(productRepository.getGetWeightOfAllFoodItems()).thenReturn(jpaResultList);

        List<ProductCustomResponse> expectedResponse = new ArrayList<>();
        expectedResponse.add(new ProductCustomResponse(1, 50));
        expectedResponse.add(new ProductCustomResponse(2, 10));

        List<ProductCustomResponse> actualResponse = productService.getWeightOfAllFoodItems();
        assertEquals(expectedResponse, actualResponse);
        verify(productRepository, times(1)).getGetWeightOfAllFoodItems();
    }

}