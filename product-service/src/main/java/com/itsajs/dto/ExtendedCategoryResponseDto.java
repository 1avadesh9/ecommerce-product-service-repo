package com.itsajs.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExtendedCategoryResponseDto extends CategoryResponseDto
{
  List<ProductResponseDto> productResponseDtoList;

  public ExtendedCategoryResponseDto(String categoryId, String categoryName, String categoryDescription,
		List<ProductResponseDto> productResponseDtoList) 
  {
	  super(categoryId, categoryName, categoryDescription);
	this.productResponseDtoList = productResponseDtoList;
  }

  
  
  
  
  
}
