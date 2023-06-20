package com.piphub.freepostapp_w9.ui.auth.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

	@SerializedName("expiresIn")
	private int expiresIn;

	@SerializedName("accessToken")
	private String accessToken;

	@SerializedName("tokenType")
	private String tokenType;

	@SerializedName("user")
	private User user;

	@SerializedName("refreshToken")
	private String refreshToken;

	public void setExpiresIn(int expiresIn){
		this.expiresIn = expiresIn;
	}

	public int getExpiresIn(){
		return expiresIn;
	}

	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public void setTokenType(String tokenType){
		this.tokenType = tokenType;
	}

	public String getTokenType(){
		return tokenType;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setRefreshToken(String refreshToken){
		this.refreshToken = refreshToken;
	}

	public String getRefreshToken(){
		return refreshToken;
	}

	@Override
 	public String toString(){
		return 
			"LoginReponse{" + 
			"expiresIn = '" + expiresIn + '\'' + 
			",accessToken = '" + accessToken + '\'' + 
			",tokenType = '" + tokenType + '\'' + 
			",user = '" + user + '\'' + 
			",refreshToken = '" + refreshToken + '\'' + 
			"}";
		}
}