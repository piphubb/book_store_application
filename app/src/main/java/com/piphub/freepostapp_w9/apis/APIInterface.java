package com.piphub.freepostapp_w9.apis;

import com.piphub.freepostapp_w9.model.Category;
import com.piphub.freepostapp_w9.model.Product;
import com.piphub.freepostapp_w9.ui.auth.models.LoginResponse;
import com.piphub.freepostapp_w9.ui.auth.models.LoginRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {
    @GET("/api/admin/category/list")
    Call<List<Category>> getAllCategory(@Header("Authorization") String auth);
    @POST("/api/admin/category/create")
    Call<Void> createCategory(@Body Category responseItem,@Header("Authorization") String auth);
    @GET("/api/admin/category/{id}")
    Call<Category> getCategoryById(@Path("id")Integer id,@Header ("Authorization") String auth);
    @POST("/api/admin/category/update")
    Call<Void> updateCategory(@Body Category responseItem,@Header("Authorization") String auth);
    @POST("/api/admin/category/delete")
    Call<Void> deleteCategory(@Body Category responseItem,@Header("Authorization") String auth);
    @GET("/api/admin/product/list")
    Call<List<Product>> getProducts(@Header("Authorization") String auth);
    @POST("/api/admin/product/create")
    Call<Void> createProduct(@Body Product req,@Header("Authorization") String auth);
    @GET("/api/admin/product/{id}")
    Call<Product> getProductById(@Path("id") Integer id,@Header("Authorization") String auth);
    @POST("/api/admin/product/delete")
    Call<Void> deleteProduct(@Body Product req,@Header("Authorization") String auth);
    @POST("/api/oauth/token")
    Call<LoginResponse> login(@Body LoginRequest req);
}
