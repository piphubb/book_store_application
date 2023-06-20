package com.piphub.freepostapp_w9.ui.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.piphub.freepostapp_w9.R;
import com.piphub.freepostapp_w9.adapter.ProductAdapter;
import com.piphub.freepostapp_w9.apis.APIClient;
import com.piphub.freepostapp_w9.apis.APIInterface;
import com.piphub.freepostapp_w9.app.BaseBackButtonActivity;
import com.piphub.freepostapp_w9.data.local.UserSharePreference;
import com.piphub.freepostapp_w9.model.Category;
import com.piphub.freepostapp_w9.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends BaseBackButtonActivity {
    private RecyclerView recyclerViewProduct;
    private ProductAdapter productAdapter;
    private APIInterface apiInterface;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        init();
        getData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                getData();
            }
        });
    }
    public void onOpenFormProductActivity(View view){
        Intent intent = new Intent(ProductActivity.this, FormProductActivity.class);
        startActivity(intent);
    }
    public void init(){
        recyclerViewProduct = findViewById(R.id.recyclerViewProduct);
        progressBar = findViewById(R.id.progressBarProduct);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        swipeRefreshLayout = findViewById(R.id.swiperefreshlayoutProduct);
    }
    private void getData(){
        progressBar.setVisibility(View.VISIBLE);
        apiInterface.getProducts(UserSharePreference.getAccessToken(this)).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                progressBar.setVisibility(View.GONE);
                productAdapter = new ProductAdapter(response.body(), ProductActivity.this, new ProductAdapter.OnClickListener() {
                    @Override
                    public void onClick(View view, Product item) {

                    }
                });
                GridLayoutManager gridLayoutManager = new GridLayoutManager(ProductActivity.this,1);
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