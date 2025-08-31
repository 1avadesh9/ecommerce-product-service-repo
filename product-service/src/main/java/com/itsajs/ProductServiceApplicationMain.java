package com.itsajs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ProductServiceApplicationMain 
{
	public static void main(String[] args) 
	{
		log.info("entered in ProductServiceApplicationMain.java main()...");
		
		SpringApplication.run(ProductServiceApplicationMain.class, args);
		
		log.info("************PRODUCT SERVICE APPLICATION STARTED************");
		
	}

}
