package com.piphub.freepostapp_w9.ui.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.piphub.freepostapp_w9.R;
import com.piphub.freepostapp_w9.adapter.CategoryBaseAdapter;
import com.piphub.freepostapp_w9.apis.APIClient;
import com.piphub.freepostapp_w9.apis.APIInterface;
import com.piphub.freepostapp_w9.app.BaseBackButtonActivity;
import com.piphub.freepostapp_w9.data.local.UserSharePreference;
import com.piphub.freepostapp_w9.model.Category;
import com.piphub.freepostapp_w9.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormProductActivity extends BaseBackButtonActivity {
    private Spinner spinnerCategory;
    private ProgressBar progressBar;
    private APIInterface apiInterface;
    private EditText productName, productNameKh, price, cost, discount;
    private Category category = new Category();
    private List<Category> categories = new ArrayList<>();
    private Product product = new Product();
    CategoryBaseAdapter categoryBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_product);
        initView();
        getCategory();
        getProductById();
    }
    public void getProductById() {
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("ID", 0);
        if (id != 0) {
            progressBar.setVisibility(View.VISIBLE);
            apiInterface.getProductById(id,UserSharePreference.getAccessToken(this)).enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    progressBar.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            product = response.body();
                            productName.setText(product.getName());
                            productNameKh.setText(product.getNameKh());
                            if (null != product.getCost()) {
                                cost.setText(product.getCost().toString());
                            }
                            if (null != product.getPrice()) {
                                price.setText(product.getPrice().toString());
                            }

                            if (null != product.getDiscount()) {
                                discount.setText(product.getDiscount().toString());
                            }
                            category = product.getCategory();
                            for (int i = 0; i < categories.size(); i++) {
                                if (category.getId() == categories.get(i).getId()) {
                                    spinnerCategory.setSelection(i);
                                    return;
                                }
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<Product> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }
    private void initView() {
        spinnerCategory = findViewById(R.id.spinnerCategory);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        productName = findViewById(R.id.editProductName);
        productNameKh = findViewById(R.id.editProductNameKhmer);
        price = findViewById(R.id.editPrice);
        cost = findViewById(R.id.editCost);
        discount = findViewById(R.id.editDiscount);
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }
    public void onClickCreate(View view) {
        if (productName.getText().toString().equals("")) {
            showToastMessage("Product is required");
            return;
        }
        if (productNameKh.getText().toString().equals("")) {
            showToastMessage("Product Khmer is required");
            return;
        }

        if (price.getText().toString().equals("")) {
            showToastMessage("Price is required");
            return;
        }
        if (cost.getText().toString().equals("")) {
            showToastMessage("Cost is required");
            return;
        }
        if (discount.getText().toString().equals("")) {
            showToastMessage("Discount is required");
            return;
        }
        product.setCategory(category);
        product.setName(productName.getText().toString());
        product.setNameKh(productNameKh.getText().toString());
        product.setPrice(Double.valueOf(price.getText().toString()));
        product.setCost(Double.valueOf(cost.getText().toString()));
        product.setDiscount(Double.valueOf(discount.getText().toString()));
        product.setStatus("ACT");
        try {
            progressBar.setVisibility(View.VISIBLE);
            apiInterface.createProduct(product,UserSharePreference.getAccessToken(this)).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    progressBar.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        } catch (Throwable e) {
            progressBar.setVisibility(View.GONE);
            showToastMessage(e.getMessage());
        }
    }
    private void getCategory() {
        apiInterface.getAllCategory(UserSharePreference.getAccessToken(this)).enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    categories.addAll(response.body());
                    category = response.body().get(0);
                    categoryBaseAdapter = new CategoryBaseAdapter(categories, FormProductActivity.this);
                    spinnerCategory.setAdapter(categoryBaseAdapter);
                    spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            category = categories.get(i);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}