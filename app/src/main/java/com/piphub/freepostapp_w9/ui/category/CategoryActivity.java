package com.piphub.freepostapp_w9.ui.category;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.piphub.freepostapp_w9.R;
import com.piphub.freepostapp_w9.adapter.CategoryAdapter;
import com.piphub.freepostapp_w9.apis.APIClient;
import com.piphub.freepostapp_w9.apis.APIInterface;
import com.piphub.freepostapp_w9.app.BaseBackButtonActivity;
import com.piphub.freepostapp_w9.data.local.UserSharePreference;
import com.piphub.freepostapp_w9.model.Category;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends BaseBackButtonActivity {
    private RecyclerView recyclerViewCategory;
    private ProgressBar progressBar;
    private APIInterface apiInterface;
    private List<Category> categoryResponseItems;
    private CategoryAdapter categoryAdapter;
    private FloatingActionButton btnCreate;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setTitle("Category");
        initView();
        getData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                getData();
            }
        });
    }
    private void initView(){

        recyclerViewCategory = findViewById(R.id.recyclerViewCategory);
        progressBar = findViewById(R.id.progressBar);
        btnCreate = findViewById(R.id.btnCreate);
        swipeRefreshLayout = findViewById(R.id.swiperefreshlayoutCategory);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, FormCategoryActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getData(){
        categoryResponseItems = new ArrayList<>();
        apiInterface = APIClient.getClient().create(APIInterface.class);
        progressBar.setVisibility(View.VISIBLE);
        apiInterface.getAllCategory(UserSharePreference.getAccessToken(this)).enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()){
                    categoryResponseItems = response.body();
                    //Toast.makeText(CategoryActivity.this,"Category Item : "+response.body().toString(), Toast.LENGTH_SHORT).show();
                    categoryAdapter = new CategoryAdapter(categoryResponseItems,CategoryActivity.this, new CategoryAdapter.OnClickListener(){
                        @Override
                        public void onClick(View view, Category item) {
                            Toast.makeText(CategoryActivity.this, "Delete",Toast.LENGTH_SHORT).show();
                            confirmDelete(item);
                        }
                    });
                    GridLayoutManager layoutManager = new GridLayoutManager(CategoryActivity.this,1);
                    recyclerViewCategory.setLayoutManager(layoutManager);
                    recyclerViewCategory.setAdapter(categoryAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getData();
    }
    private void confirmDelete(Category item){
        AlertDialog.Builder builder = new AlertDialog.Builder(CategoryActivity.this);

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
            apiInterface.deleteCategory(item,UserSharePreference.getAccessToken(this)).enqueue(new Callback<Void>() {
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