package com.itsajs.model;

import java.util.List;

import com.itsajs.config.IdGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Data
@ToString
@Table(name="TBL_CATEGORY")
public class Category 
{
  @Id
  //@GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "CATEGORY_ID")
  private String categoryId;			//used in ProductRequestDto.java
  
  @Column(name = "CATEGORY_NAME")
  private String categoryName;		//used in ProductResponseDto.java
  
  @Column(name = "CATEGORY_DESCRIPTION")
  private String categoryDescription;
  
  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Product> productList;
  
  @PrePersist
  public void generateCategoryId()
  {
	 if(this.categoryId == null)
	 {
		 this.categoryId = "cat-"+String.format("%05d", IdGenerator.getNextCategoryId());
	 }
  }
  
  
}
