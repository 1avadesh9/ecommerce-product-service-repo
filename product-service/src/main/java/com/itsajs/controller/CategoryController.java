package com.itsajs.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsajs.dto.CategoryRequestDto;
import com.itsajs.dto.CategoryResponseDto;
import com.itsajs.dto.ExtendedCategoryResponseDto;
import com.itsajs.service.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor  //constructor dependency injection
@RequestMapping("/api/v1/categories")
@Slf4j
public class CategoryController
{
    private final CategoryService categoryService; //for constructor injection final required
	
	/*
	 * public CategoryController(CategoryService categoryService) 
	 * {
	 *    this.categoryService = categoryService; 
	 * }
	 */  //equivalent to @RequiredArgsConstructor
    
    @PostMapping("/")
    public ResponseEntity<CategoryResponseDto> saveCategoryDetails(@RequestBody CategoryRequestDto categoryRequestDto) 
    {
    	log.info("entered in CategoryController.java saveCategoryDetails()..."+categoryRequestDto);
    	
    	CategoryResponseDto savedCategoryDetails = this.categoryService.saveCategoryDetails(categoryRequestDto);
    	
    	return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoryDetails);
    }
    
    @GetMapping("/")
    public ResponseEntity<List<ExtendedCategoryResponseDto>> getAllCategoryDetails() 
    {
    	log.info("entered in CategoryController.java getAllCategoryDetails()...");
    	
        List<ExtendedCategoryResponseDto> categoriesList = this.categoryService.getAllCategoryDetails();
        //return ResponseEntity.ok(categoriesList);
       return ResponseEntity.status(HttpStatus.OK).body(categoriesList);
    }
    
    @GetMapping("/{categoryId}")
    public ResponseEntity<ExtendedCategoryResponseDto> getCategoryDetailsById(@PathVariable("categoryId") String categoryId) 
    {
    	log.info("entered in CategoryController.java getCategoryDetailsById()...");
    	
    	ExtendedCategoryResponseDto extendedCategoryResponseDto = this.categoryService.getCategoryDetailsById(categoryId);
    	
    	return ResponseEntity.status(HttpStatus.OK).body(extendedCategoryResponseDto);
    }

}
