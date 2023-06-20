package com.piphub.freepostapp_w9.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CategoryResponse{

	@SerializedName("CategoryResponse")
	private List<Category> categoryResponse;

	public void setCategoryResponse(List<Category> categoryResponse){
		this.categoryResponse = categoryResponse;
	}

	public List<Category> getCategoryResponse(){
		return categoryResponse;
	}

	@Override
 	public String toString(){
		return 
			"CategoryResponse{" + 
			"categoryResponse = '" + categoryResponse + '\'' + 
			"}";
		}
}