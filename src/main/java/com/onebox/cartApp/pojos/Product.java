package com.onebox.cartApp.pojos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
	
	public long productId;
	public String description;
	public String collection;
	public String condition;
	public long price;
	public long amount;
	
	public Product(long productId, String description, String collection, String condition, long price, long amount) {
		super();
		this.productId = productId;
		this.description = description;
		this.collection = collection;
		this.condition = condition;
		this.price = price;
		this.amount = amount;
	}
	

}
