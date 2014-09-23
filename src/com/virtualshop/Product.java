package com.virtualshop;

import java.io.Serializable;

public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	String description;
	Double price;
	String name;
	
	public Product(String name,Double price,String description)
	{
		this.name = name;
		this.price = price;
		this.description = description;
	}

}
