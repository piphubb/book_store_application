package com.piphub.freepostapp_w9.ui.auth.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("firstName")
	private String firstName;

	@SerializedName("lastName")
	private String lastName;

	@SerializedName("password")
	private String password;

	@SerializedName("phoneNumber")
	private String phoneNumber;

	@SerializedName("profile")
	private String profile;

	@SerializedName("roles")
	private List<Role> roles;

	@SerializedName("id")
	private int id;

	@SerializedName("verifyEmail")
	private Object verifyEmail;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	@SerializedName("status")
	private String status;

	@SerializedName("changePassword")
	private String changePassword;

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setProfile(String profile){
		this.profile = profile;
	}

	public String getProfile(){
		return profile;
	}

	public void setRoles(List<Role> roles){
		this.roles = roles;
	}

	public List<Role> getRoles(){
		return roles;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setVerifyEmail(Object verifyEmail){
		this.verifyEmail = verifyEmail;
	}

	public Object getVerifyEmail(){
		return verifyEmail;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setChangePassword(String changePassword){
		this.changePassword = changePassword;
	}

	public String getChangePassword(){
		return changePassword;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"firstName = '" + firstName + '\'' + 
			",lastName = '" + lastName + '\'' + 
			",password = '" + password + '\'' + 
			",phoneNumber = '" + phoneNumber + '\'' + 
			",profile = '" + profile + '\'' + 
			",roles = '" + roles + '\'' + 
			",id = '" + id + '\'' + 
			",verifyEmail = '" + verifyEmail + '\'' + 
			",email = '" + email + '\'' + 
			",username = '" + username + '\'' + 
			",status = '" + status + '\'' + 
			",changePassword = '" + changePassword + '\'' + 
			"}";
		}
}