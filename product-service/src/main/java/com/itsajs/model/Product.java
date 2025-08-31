package com.itsajs.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

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
@ToString
@Entity
@Table(name="TBL_PRODUCT")
public class Product 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRODUCT_ID")
	private String productId;
	
	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	@Column(name = "PRODUCT_DESCRIPTION")
	private String productDescription;
	
	@Column(name = "PRODUCT_PRICE")
	private Double productPrice;
	
	@Column(name = "STOCK_QUANTITY")
	private Integer stockQuantity;
	
	@Column(name = "INSTOCK")
	private Boolean inStock;
	
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
	   
	   
	   
   }
   
}
