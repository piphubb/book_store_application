package com.piphub.freepostapp_w9.ui.product;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.piphub.freepostapp_w9.R;
import com.piphub.freepostapp_w9.adapter.ProductAdapter;
import com.piphub.freepostapp_w9.apis.APIClient;
import com.piphub.freepostapp_w9.apis.APIInterface;
import com.piphub.freepostapp_w9.app.BaseBackButtonActivity;
import com.piphub.freepostapp_w9.data.local.UserSharePreference;
import com.piphub.freepostapp_w9.model.Category;
import com.piphub.freepostapp_w9.model.Product;
import com.piphub.freepostapp_w9.ui.category.CategoryActivity;

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
                        Toast.makeText(ProductActivity.this, "Delete",Toast.LENGTH_SHORT).show();
                        confirmDelete(item);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getData();
    }
    private void confirmDelete(Product item){
        AlertDialog.Builder builder = new AlertDialog.Builder(ProductActivity.this);

        // Set the message show for the Alert time
        builder.setMessage("Do you want to exit ?");

        // Set Alert Title
        builder.setTitle("Alert !");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(true);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            progressBar.setVisibility(View.VISIBLE);
            apiInterface.deleteProduct(item,UserSharePreference.getAccessToken(this)).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    progressBar.setVisibility(View.GONE);
                    getData();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        });

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }
}