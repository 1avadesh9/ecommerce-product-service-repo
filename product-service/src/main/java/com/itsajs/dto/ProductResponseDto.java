package com.itsajs.dto;

import lombok.Data;

@Data
public class ProductResponseDto 
{
	private String productId;	
	private String productName;   
	private String productDescription;
	private Double productPrice;
	private Integer stockQuantity;
	private Boolean inStock;
	private String categoryName;
}
