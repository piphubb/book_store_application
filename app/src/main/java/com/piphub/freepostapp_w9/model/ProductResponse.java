package com.piphub.freepostapp_w9.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProductResponse{

	@SerializedName("ProductResponse")
	private List<Product> productResponse;

	public void setProductResponse(List<Product> productResponse){
		this.productResponse = productResponse;
	}

	public List<Product> getProductResponse(){
		return productResponse;
	}
}