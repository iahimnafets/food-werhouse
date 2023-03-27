package com.food.warehouse.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.food.warehouse.dto.Response;
import com.food.warehouse.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@Slf4j
@Path("/warehouse")
@Produces(MediaType.APPLICATION_JSON)
public class WarehouseController  {

    @Autowired
    private ProductService productService;

    @GET
    @Produces("application/json")
    @Path("/product/{id}")
    public ResponseEntity<Response> getProductById(@PathParam(value = "id") Long orderId)   {

        return ResponseEntity.ok(
                Response.builder()
                        .status(HttpStatus.OK)
                        .data(Map.of("product", productService.getProductById(orderId) ) )
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }

    @GET
    @Produces("application/json")
    @Path("/product/all")
    public ResponseEntity<Response> getAllProducts( )  {

        return ResponseEntity.ok(
                Response.builder()
                        .status(HttpStatus.OK)
                        .data(Map.of("all-products", productService.getAllProducts() ) )
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GET
    @Produces("application/json")
    @Path("/product/vegetables")
    public ResponseEntity<Response> getProductsVegetables( )  {

        return ResponseEntity.ok(
                Response.builder()
                        .status(HttpStatus.OK)
                        .data(Map.of("vegetables", productService.getProductsVegetables() ) )
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GET
    @Produces("application/json")
    @Path("/product/all-weight")
    public ResponseEntity<Response> getWeightOfAllFoodItems( )   {

        return ResponseEntity.ok(
                Response.builder()
                        .status(HttpStatus.OK)
                        .data(Map.of("all-weight", productService.getWeightOfAllFoodItems() ) )
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GET
    @Produces("application/json")
    @Path("/product/all-weight-vegetables")
    public ResponseEntity<Response> getWeightOfAllVegetables( )  {

        return ResponseEntity.ok(
                Response.builder()
                        .status(HttpStatus.OK)
                        .data(Map.of("all-weight-vegetables", productService.getWeightOfAllVegetables() ) )
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GET
    @Produces("application/json")
    @Path("/product/all-weight-category")
    public ResponseEntity<Response> getWeightOfAllFoodItemsByCategory( )  {

        return ResponseEntity.ok(
                Response.builder()
                        .status(HttpStatus.OK)
                        .data(Map.of("weigh-category", productService.getWeightOfAllFoodItemsByCategory() ) )
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GET
    @Produces("application/json")
    @Path("/product/calculate-discount")
    public ResponseEntity<Response> getProductsWithDiscount( )  {

        return ResponseEntity.ok(
                Response.builder()
                        .status(HttpStatus.OK)
                        .data(Map.of("calculate-discount", productService.getProductsWithDiscount() ) )
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }




    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}