package com.itsajs.mapper;

import com.itsajs.dto.ProductResponseDto;
import com.itsajs.model.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductMapping
{
   public static ProductResponseDto productToProductResponseDto(Product product)
   {
	   log.info("entered in ProductMapping.java productToProductResponseDto()... "+product);
	   
	   ProductResponseDto productResponseDto = new ProductResponseDto();
	   productResponseDto.setProductId(product.getProductId());
	   productResponseDto.setProductName(product.getProductName());
	   productResponseDto.setProductDescription(product.getProductDescription());
	   productResponseDto.setProductPrice(product.getProductPrice());
	   productResponseDto.setInStock(product.getInStock());
	   productResponseDto.setStockQuantity(product.getStockQuantity());
	   productResponseDto.setCategoryName(product.getCategory().getCategoryName());
	   
	   return productResponseDto;
   }
   
   public Product productResponseDtoToProduct(ProductResponseDto productResponseDto)
   {
	   log.info("entered in ProductMapping.java productToProductResponseDto()... "+productResponseDto);
	   
	   Product product = new Product();
	   product.setProductId(productResponseDto.getProductId());
	   product.setProductName(productResponseDto.getProductName());
	   product.setProductDescription(productResponseDto.getProductDescription());
	   product.setProductPrice(productResponseDto.getProductPrice());
	   product.setInStock(productResponseDto.getInStock());
	   product.setStockQuantity(productResponseDto.getStockQuantity());
	   
	   return product;
   }
}
