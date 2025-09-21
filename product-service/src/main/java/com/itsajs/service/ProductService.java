package com.itsajs.service;

import java.util.List;

import com.itsajs.dto.ProductRequestDto;
import com.itsajs.dto.ProductResponseDto;

public interface ProductService
{
   public ProductResponseDto saveProductDetails(ProductRequestDto productRequestDto);
   
   public ProductResponseDto getProductDetailsById(String productId);
   
   public List<ProductResponseDto> getAllProductDetails();
   
   public ProductResponseDto updateStock(String productId, Integer stockQuantity);
   
}
