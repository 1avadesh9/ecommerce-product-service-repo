package com.itsajs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsajs.model.Product;

public interface ProductRepository extends JpaRepository<Product, String>
{

}
