package com.itsajs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itsajs.dto.CategoryRequestDto;
import com.itsajs.dto.CategoryResponseDto;
import com.itsajs.dto.ExtendedCategoryResponseDto;
import com.itsajs.dto.ProductResponseDto;
import com.itsajs.entity.Category;
import com.itsajs.entity.Product;
import com.itsajs.mapper.CategoryMapping;
import com.itsajs.mapper.ProductMapping;
import com.itsajs.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("categoryService")
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService
{
	private final CategoryRepository categoryRepository;  //for constructor dependency injection
	
	@Override
	public CategoryResponseDto saveCategoryDetails(CategoryRequestDto categoryRequestDto) 
	{
		log.info("entered in CategoryServiceImpl.java saveCategoryDetails()... "+categoryRequestDto);
		
		Category category = new Category();
		category.setCategoryName(categoryRequestDto.getCategoryName());
		category.setCategoryDescription(categoryRequestDto.getCategoryDescription());
		
		Category savedCategory = this.categoryRepository.save(category);
		
		return CategoryMapping.categoryToCategoryResponseDto(savedCategory);
	}

	@Override
	public ExtendedCategoryResponseDto getCategoryDetailsById(String categoryId) 
	{
		log.info("entered in CategoryServiceImpl.java getCategoryDetailsById()..."+categoryId);
		
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Category not found"));
		
	  return convertToExtendedCategoryResponseDto(category);
	}

	@Override
	public List<ExtendedCategoryResponseDto> getAllCategoryDetails() 
	{
		log.info("entered in CategoryServiceImpl.java getAllCategoryDetails()... ");
		
		List<Category> categoryList = this.categoryRepository.findAll();
		
	    List<ExtendedCategoryResponseDto> extendedCategoryResponseDtoList = new ArrayList<ExtendedCategoryResponseDto>();
	
	    for(Category category : categoryList)
	    {
	    	ExtendedCategoryResponseDto extendedCategoryResponseDto = new ExtendedCategoryResponseDto();
	    	extendedCategoryResponseDto.setCategoryId(category.getCategoryId());
	    	extendedCategoryResponseDto.setCategoryName(category.getCategoryName());
	    	extendedCategoryResponseDto.setCategoryDescription(category.getCategoryDescription());
	    	
	    	List<ProductResponseDto> productResponseDtoList = new ArrayList<ProductResponseDto>();
	    	List<Product> productList = category.getProductList();
	    	
	    	extendedCategoryResponseDto.setProductResponseDtoList(
	    			productList.stream().map(ProductMapping::productToProductResponseDto).toList());
	    	
	    	extendedCategoryResponseDtoList.add(extendedCategoryResponseDto);
	    }
	    
	    return extendedCategoryResponseDtoList;
	}

	@Override
	public CategoryResponseDto updateStock(String categoryId, CategoryResponseDto categoryRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCategory() {
		// TODO Auto-generated method stub
		
	}
  
	private ExtendedCategoryResponseDto convertToExtendedCategoryResponseDto(Category category)
	{
		List<Product> productList = category.getProductList();
		List<ProductResponseDto> productResponseDtoList = productList.stream().map(ProductMapping::productToProductResponseDto).toList();
	
	return new ExtendedCategoryResponseDto(category.getCategoryId(), category.getCategoryName(),
			category.getCategoryDescription(),productResponseDtoList);
	}
	
}
