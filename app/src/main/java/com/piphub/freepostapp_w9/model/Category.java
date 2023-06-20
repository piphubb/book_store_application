package com.piphub.freepostapp_w9.model;

import com.google.gson.annotations.SerializedName;

public class Category{

	@SerializedName("nameKh")
	private String nameKh;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("status")
	private String status;

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

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}