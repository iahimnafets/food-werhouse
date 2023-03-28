package com.food.warehouse;

import com.food.warehouse.entities.Category;
import com.food.warehouse.entities.Discount;
import com.food.warehouse.entities.Product;
import com.food.warehouse.entities.Supplier;
import com.food.warehouse.service.CategoryService;
import com.food.warehouse.service.DiscountService;
import com.food.warehouse.service.ProductService;
import com.food.warehouse.service.SupplierService;
import com.food.warehouse.utilities.WarehouseUtils;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class WarehouseApplication {


	/**
     * Mihai Stefan => March 26, 2023
	 * @param args˙
	 */
	public static void main(String[] args) {
		try {
	     	SpringApplication.run(WarehouseApplication.class, args);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	CommandLineRunner run(ProductService productService,
						   CategoryService categoryService,
						  SupplierService supplierService,
						  DiscountService discountService) {

			return args -> {
					createCategories(categoryService);
					createSuppliers(supplierService);
					createDiscount(discountService);
					createProduct(productService);
			};
	}

	private void createDiscount(DiscountService discountService) {

		Discount discount1 = Discount.builder()
				.discountPercentage(new BigDecimal(2))
				.discountStartDate(new Date() ) // This discounter Start today
				.discountEndDate(WarehouseUtils.getNewDate(10)) // for 10 days
				.discountName( " Discount for SPRING period 2% ")
				.build();

		Discount discount2 = Discount.builder()
				.discountPercentage(new BigDecimal(5))
				.discountStartDate(new Date() ) // This discounter. Start today
				.discountEndDate(WarehouseUtils.getNewDate(10))// for 10 days
				.discountName( " Discount2 5%")
				.build();

		Discount discount3 = Discount.builder()
				.discountPercentage(new BigDecimal(10))
				.discountStartDate(WarehouseUtils.getNewDate(10)) // This discounter. Start later
				.discountEndDate(WarehouseUtils.getNewDate(5)) // for 5 days
				.discountName( " Discount2 10%")
				.build();

		List<Discount> listDiscounts = new ArrayList<>();
		listDiscounts.add(discount1);
		listDiscounts.add(discount2);
		listDiscounts.add(discount3);
		discountService.saveAll(listDiscounts);
	}

	private void createSuppliers(SupplierService supplierService) {

		Supplier supplier1 = Supplier.builder()
				.supplierName("John's Farm")
				.supplierContactInfo("johnsfarm@example.com")
				.supplierAddress("123 Main St, Anytown USA")
				.build();

		Supplier supplier2 = Supplier.builder()
				.supplierName("Giovanni Franco")
				.supplierContactInfo("giovanni@example.com")
				.supplierAddress("Via della Vittoria 45 Torino, Italy")
				.build();

		Supplier supplier3 = Supplier.builder()
				.supplierName("Mihai Stefan")
				.supplierContactInfo("mihai@example.com")
				.supplierAddress("Strada Tudor Vladimirescu 89 Bucuresti, Romania ")
				.build();

		Supplier supplier4 = Supplier.builder()
				.supplierName("Mihai Stefan")
				.supplierContactInfo("mihai@example.com")
				.supplierAddress("Strada Tudor Vladimirescu 89 Bucuresti, Romania ")
				.build();

		supplierService.save( supplier1 );
		supplierService.save( supplier2 );
		supplierService.save( supplier3 );
	// 	supplierService.save( supplier4 );  cannot be entered, it has an email identical to another ( supplier3 )

	}

	private void createProduct(ProductService productService) {

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
				.discount( new Discount(1L, null, null, null, null ) )
				.build();

		Product product3 = Product.builder()
				.productName("potatoes")
				.productDescription("Fresh potatoes")
				.category(new Category(2L, null, null))
				.unitPrice(new BigDecimal("1.39"))
				.unitOfMeasure("kg")
				.supplier( new Supplier(2L , null , null , null))
				.quantityOnHand(1000)
				.build();

		Product product4 = Product.builder()
				.productName("peppers")
				.productDescription("Fresh peppers")
				.category(new Category(2L, null, null))
				.unitPrice(new BigDecimal("1.10"))
				.unitOfMeasure("kg")
				.supplier( new Supplier(2L , null , null , null))
				.quantityOnHand(2000)
				.build();

		Product product5 = Product.builder()
				.productName("cucumbers")
				.productDescription("Fresh cucumbers")
				.category(new Category(2L, null, null))
				.unitPrice(new BigDecimal("1.90"))
				.unitOfMeasure("kg")
				.supplier( new Supplier(3L , null , null , null))
				.quantityOnHand(2500)
				.build();

		Product product6 = Product.builder()
				.productName("Apple")
				.productDescription("Fresh and delicious apples")
				.category(new Category(1L, null, null))
				.unitPrice(new BigDecimal("1.99"))
				.unitOfMeasure("kg")
				.quantityOnHand(350)
				.supplier( new Supplier(3L , null , null , null))
				.discount( new Discount(2L, null, null, null, null ) )
				.build();

		Product product7 = Product.builder()
				.productName("Carrot")
				.productDescription("Crunchy and nutritious carrots")
				.category(new Category(2L, null, null))
				.unitPrice(new BigDecimal("0.99"))
				.unitOfMeasure("kg")
				.quantityOnHand(200)
				.supplier( new Supplier(3L , null , null , null))
				.build();

		Product product8 = Product.builder()
				.productName("Oranges")
				.productDescription("Sicilian tasty oranges")
				.category(new Category(3L, null, null))
				.unitPrice(new BigDecimal("0.90"))
				.unitOfMeasure("kg")
				.quantityOnHand(500)
				.supplier( new Supplier(2L , null , null , null))
				.discount( new Discount(3L, null, null, null, null ) )
				.build();

		List<Product> listProduct = new ArrayList<>();
		listProduct.add( product1 );
		listProduct.add( product2 );
		listProduct.add( product3 );
		listProduct.add( product4 );
		listProduct.add( product5 );

		listProduct.add( product6 );
		listProduct.add( product7 );
		listProduct.add( product8 );
		productService.saveAll( listProduct );
	}


	private void createCategories (CategoryService categoryService ){
		Category beverages = Category.builder()
				.categoryName("Beverages")
				.categoryDescription("Beverages Description ")
				.build();

		Category vegetables = Category.builder()
				.categoryName("Vegetables")
				.categoryDescription("vegetables Description")
				.build();

		Category fruits = Category.builder()
				.categoryName("Fruits")
				.categoryDescription("Fruits Description")
				.build();

		Category oilsSolidFats = Category.builder()
				.categoryName("Oils & Solid Fats")
				.categoryDescription("oilsSolidFats Description")
				.build();

		Category addedSugars = Category.builder()
				.categoryName("Added Sugars")
				.categoryDescription("Added Sugars Description ")
				.build();

		List<Category> listCategory = new ArrayList<>();
		listCategory.add(beverages);
		listCategory.add(vegetables);
		listCategory.add(fruits);
		listCategory.add(oilsSolidFats);
		listCategory.add(addedSugars);

		categoryService.saveAll( listCategory );
	}

}
