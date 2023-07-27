package com.piphub.freepostapp_w9;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.piphub.freepostapp_w9.adapter.ProductAdapter;
import com.piphub.freepostapp_w9.apis.APIClient;
import com.piphub.freepostapp_w9.apis.APIInterface;
import com.piphub.freepostapp_w9.app.BaseActivity;
import com.piphub.freepostapp_w9.data.local.UserSharePreference;
import com.piphub.freepostapp_w9.model.Product;
import com.piphub.freepostapp_w9.ui.auth.LoginActivity;
import com.piphub.freepostapp_w9.ui.category.CategoryActivity;
import com.piphub.freepostapp_w9.ui.dashboard.DashboardActivity;
import com.piphub.freepostapp_w9.ui.product.ProductActivity;
import com.piphub.freepostapp_w9.ui.profile.ProfileActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity{
    private APIInterface apiInterface;
    private Button btnOpenCategory;

    private DrawerLayout drawerLayout;
    private ImageView menu;

    private RecyclerView recyclerViewProduct;
    private ProductAdapter productAdapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home Screen");
        init();
        getData();

//        btnOpenCategory = findViewById(R.id.btnListData);
//        btnOpenCategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
//                startActivity(intent);
//            }
//        });

        //new
        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });
        //new


//        apiInterface = APIClient.getClient().create(APIInterface.class);
//        apiInterface.getAllCategory().enqueue(new Callback<List<CategoryResponseItem>>() {
//            @Override
//            public void onResponse(Call<List<CategoryResponseItem>> call, Response<List<CategoryResponseItem>> response) {
//                if(response.isSuccessful()){
//                    //Toast.makeText(MainActivity.this,"Category Item : "+response.body().toString(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<CategoryResponseItem>> call, Throwable t) {
//
//            }
//        });

    }

    //new

    public static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public static void closeDrawer(DrawerLayout drawerLayout){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public static void redirectActivity(Activity activity, Class secondActivity){
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    //new
    public void openCategoryActivity(View view){
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        startActivity(intent);
    }
    public void openProductActivity(View view){
        Intent intent = new Intent(MainActivity.this, ProductActivity.class);
        startActivity(intent);
    }
    public void onClickLogout(View view){
        UserSharePreference.clearUser(this);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void openProfileActivity(View view){
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    //
    public void init(){
        recyclerViewProduct = findViewById(R.id.recyclerViewProduct);
        progressBar = findViewById(R.id.progressBar);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        swipeRefreshLayout = findViewById(R.id.swiperefreshlayoutProduct);
    }
    private void getData(){
        progressBar.setVisibility(View.VISIBLE);
        apiInterface.getProducts(UserSharePreference.getAccessToken(this)).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                progressBar.setVisibility(View.GONE);
                productAdapter = new ProductAdapter(response.body(), MainActivity.this, new ProductAdapter.OnClickListener() {
                    @Override
                    public void onClick(View view, Product item) {
                        Toast.makeText(MainActivity.this, "Delete",Toast.LENGTH_SHORT).show();
                    }
                });
                GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,3);
                recyclerViewProduct.setLayoutManager(gridLayoutManager);
                recyclerViewProduct.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}