package com.itsajs.dto;

import lombok.Data;

@Data
public class ProductRequestDto 
{
	private String productName;   
	private String productDescription;
	private Double productPrice;
	private Integer stockQuantity;
	private String categoryId;
	
}
