package com.itsajs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itsajs.dto.ProductRequestDto;
import com.itsajs.dto.ProductResponseDto;
import com.itsajs.entity.Category;
import com.itsajs.entity.Product;
import com.itsajs.repository.CategoryRepository;
import com.itsajs.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("productService")
@Slf4j
@RequiredArgsConstructor  //constructor depencency injection, no need for @Autowired i.e field injection
public class ProductServiceImpl implements ProductService
{
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	
	@Override
	public ProductResponseDto saveProductDetails(ProductRequestDto productRequestDto)
	{
		log.info("entered in ProductServiceImpl.java saveProductDetails()...");
		
		Category category = this.categoryRepository.findById(productRequestDto.getCategoryId()).orElseThrow(()-> new RuntimeException("Category not found"));
		
		Product product = new Product();
		product.setProductName(productRequestDto.getProductName());
		product.setProductDescription(productRequestDto.getProductDescription());
		product.setProductPrice(productRequestDto.getProductPrice());
		product.setStockQuantity(productRequestDto.getStockQuantity());
		product.setCategory(category);
		
		Product savedProduct = this.productRepository.save(product);
		
		return convertProductToProductResponseDto(savedProduct);
	}

	//mapper converts Product object To ProductResponseDto to return ProductResponseDto back
	public ProductResponseDto convertProductToProductResponseDto(Product product)
	{
		ProductResponseDto productResponseDto = new ProductResponseDto();
		productResponseDto.setProductId(product.getProductId());
		productResponseDto.setProductName(product.getProductName());
		productResponseDto.setProductDescription(product.getProductDescription());
		productResponseDto.setProductPrice(product.getProductPrice());
		productResponseDto.setStockQuantity(product.getStockQuantity());
		productResponseDto.setInStock(product.getInStock());
		productResponseDto.setCategoryName(product.getCategory().getCategoryName());
		
		return productResponseDto;
	}
	
	@Override
	public ProductResponseDto getProductDetailsById(String productId)
	{
		log.info("entered in ProductServiceImpl.java getProductDetailsById(String productId)... "+productId);
		Product product = this.productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product not found"));
		
		return convertProductToProductResponseDto(product);
	}

	@Override
	public List<ProductResponseDto> getAllProductDetails()
	{
		log.info("entered in ProductServiceImpl.java getAllProductDetails()...");
		
		List<Product> productList = this.productRepository.findAll();
		List<ProductResponseDto> productResponseDtoList = productList.stream().map(this::convertProductToProductResponseDto).toList();
		
		return productResponseDtoList;
	}

	@Override
	public ProductResponseDto updateStock(String productId, Integer stockQuantity) 
	{
		log.info("entered in ProductServiceImpl.java updateStock(String productId, Integer stockQuantity)..."+
				productId+" "+stockQuantity);
		
		Product product = this.productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product not found"));
		product.setStockQuantity(stockQuantity);
		
		this.productRepository.save(product);
		
	 return convertProductToProductResponseDto(product);
	}

	public void deleteProductDetailsById(String productId)
	{
		log.info("entered in ProductServiceImpl.java deleteProductDetailsById()..."+productId);
		
		Product product = this.productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product not found"));
		
		this.productRepository.delete(product);
	}
	
}
