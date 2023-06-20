package com.piphub.freepostapp_w9.model;

import com.google.gson.annotations.SerializedName;

public class Product {

	@SerializedName("cost")
	private Double cost;

	@SerializedName("price")
	private Double price;

	@SerializedName("nameKh")
	private String nameKh;

	@SerializedName("name")
	private String name;

	@SerializedName("discount")
	private Double discount;

	@SerializedName("id")
	private int id;

	@SerializedName("category")
	private Category category;

	@SerializedName("status")
	private String status;

	public void setCost(Double cost){
		this.cost = cost;
	}

	public Double getCost(){
		return cost;
	}

	public void setPrice(Double price){
		this.price = price;
	}

	public Double getPrice(){
		return price;
	}

	public void setNameKh(String nameKh){
		this.nameKh = nameKh;
	}

	public String getNameKh(){
		return nameKh;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDiscount(Double discount){
		this.discount = discount;
	}

	public Double getDiscount(){
		return discount;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCategory(Category category){
		this.category = category;
	}

	public Category getCategory(){
		return category;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}