package com.onebox.cartApp.pojos;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cart {
	
	public long id;
	public List<Product> products;
	public long total;
	public Date timestamp;
	
	public Cart(long id, List<Product> products, long total, Date timestamp) {
		super();
		this.id = id;
		this.products = products;
		this.total = total;
		this.timestamp = timestamp;
	}
	
	
	

}
