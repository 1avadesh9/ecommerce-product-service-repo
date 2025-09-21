package com.itsajs.service;

import java.util.List;

import com.itsajs.dto.CategoryRequestDto;
import com.itsajs.dto.CategoryResponseDto;
import com.itsajs.dto.ExtendedCategoryResponseDto;

public interface CategoryService 
{
	public CategoryResponseDto saveCategoryDetails(CategoryRequestDto categoryRequestDto);
	   
	   public ExtendedCategoryResponseDto getCategoryDetailsById(String categoryId);
	   
	   public List<ExtendedCategoryResponseDto> getAllCategoryDetails();
	   
	   public CategoryResponseDto updateStock(String categoryId, CategoryResponseDto categoryRequestDto);
	   
	   public void deleteCategory();
}
