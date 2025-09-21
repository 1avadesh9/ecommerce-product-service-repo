package com.itsajs.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itsajs.dto.ProductRequestDto;
import com.itsajs.dto.ProductResponseDto;
import com.itsajs.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController 
{
   private final ProductService productService;
   
   @PostMapping("/")
   public ResponseEntity<ProductResponseDto> saveProductDetails(@RequestBody ProductRequestDto productRequestDto)
   {
	   log.info("entered in ProductController.java saveProductDetails()..."+productRequestDto);
	   
	   ProductResponseDto savedProductDetails = this.productService.saveProductDetails(productRequestDto);
	   
	  return ResponseEntity.status(HttpStatus.CREATED).body(savedProductDetails);
   }
   
    @GetMapping("/{productId}")
	public ResponseEntity<ProductResponseDto> getProductDetailsById(@PathVariable("productId") String productId)
	{
		log.info("entered in ProductController.java getProductDetailsById(String productId)... "+productId);
		
		ProductResponseDto productResponseDto = this.productService.getProductDetailsById(productId);
		
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
	}

	@GetMapping("/")
	public ResponseEntity<List<ProductResponseDto>> getAllProductDetails()
	{
		log.info("entered in ProductController.java getAllProductDetails()...");
		
		List<ProductResponseDto> productResponseDtoList = this.productService.getAllProductDetails();
		
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDtoList);
	}

	//http://127.0.0.1:8081/api/v1/products/prod-00001?stockQuantity=10
	@PatchMapping("/{productId}")  //only update particular field not entire object
	public ResponseEntity<ProductResponseDto> updateStock(@PathVariable("productId") String productId, 
			@RequestParam Integer stockQuantity) 
	{
		log.info("entered in ProductController.java updateStock(String productId, Integer stockQuantity)..."+
				productId+" "+stockQuantity);
		
		ProductResponseDto updatedProductResponseDto = this.productService.updateStock(productId, stockQuantity);
		
	 return ResponseEntity.status(HttpStatus.OK).body(updatedProductResponseDto);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<String> deleteProductDetailsById(@PathVariable("productId") String productId)
	{
		log.info("entered in ProductController.java deleteProductDetailsById()..."+productId);
		
		this.productService.deleteProductDetailsById(productId);
		
		return ResponseEntity.status(HttpStatus.OK).body("Product Details with Product Id "+productId+
				" deleted successfully...");
	}
   
   
}
