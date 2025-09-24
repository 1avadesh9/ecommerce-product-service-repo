package com.itsajs.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.itsajs.config.IdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "category")  //prevents printing and StackOverflowError Exception
@Entity
@Table(name="TBL_PRODUCT")
public class Product 
{
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRODUCT_ID")
	private String productId;				//used in ProductResponseDto.java
	
	@Column(name = "PRODUCT_NAME")
	private String productName;      		//used in ProductRequestDto.java
	
	@Column(name = "PRODUCT_DESCRIPTION")
	private String productDescription;		//used in ProductRequestDto.java
	
	@Column(name = "PRODUCT_PRICE")
	private Double productPrice;			//used in ProductRequestDto.java
	
	@Column(name = "STOCK_QUANTITY")
	private Integer stockQuantity;			//used in ProductRequestDto.java
	
	@Column(name = "INSTOCK")
	private Boolean inStock;				//used in ProductResponseDto.java
	
	@Column(name = "PRODUCT_CREATED_DT")
	private LocalDateTime productCreatedDate;
	
	@Column(name = "PRODUCT_UPDATED_DT")
	private LocalDateTime productUpdatedDate;
	
   @ManyToOne
   @JoinColumn(name = "CATEGORY_ID")
   private Category category;
   
   @PrePersist
   @PreUpdate
   public void updateStockStatus()
   {
	   this.inStock = this.stockQuantity != null && this.stockQuantity > 0;
	   
	   if(this.productCreatedDate == null)
	   
		   this.productCreatedDate = LocalDateTime.now();
		   this.productUpdatedDate = LocalDateTime.now();
	   
		if(this.productId == null)
		{
		   this.productId = "prod-"+String.format("%05d", IdGenerator.getNextProductId());
		}
	   
   }
   
}
