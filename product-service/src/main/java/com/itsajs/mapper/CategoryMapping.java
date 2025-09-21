package com.itsajs.mapper;

import com.itsajs.dto.CategoryResponseDto;
import com.itsajs.model.Category;

public class CategoryMapping 
{
  public static CategoryResponseDto categoryToCategoryResponseDto(Category category)
  {
	  CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
	  categoryResponseDto.setCategoryId(category.getCategoryId());
	  categoryResponseDto.setCategoryName(category.getCategoryName());
	  categoryResponseDto.setCategoryDescription(category.getCategoryDescription());
	  
	  return categoryResponseDto;
  }
  
  public static Category categoryResponseDtoToCategory(CategoryResponseDto categoryResponseDto)
  {
	  Category category = new Category();
	  category.setCategoryId(categoryResponseDto.getCategoryId());
	  category.setCategoryName(categoryResponseDto.getCategoryName());
	  category.setCategoryDescription(categoryResponseDto.getCategoryDescription());
	  
	  return category;
  }
  
}
