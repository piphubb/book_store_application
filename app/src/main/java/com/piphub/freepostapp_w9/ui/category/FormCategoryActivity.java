package com.piphub.freepostapp_w9.ui.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.piphub.freepostapp_w9.R;
import com.piphub.freepostapp_w9.apis.APIClient;
import com.piphub.freepostapp_w9.apis.APIInterface;
import com.piphub.freepostapp_w9.app.BaseBackButtonActivity;
import com.piphub.freepostapp_w9.model.Category;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormCategoryActivity extends BaseBackButtonActivity {
    private Button btnCreate;
    private EditText editName,editNameKh;
    private ProgressBar progressBar;
    private APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_category);
        initView();
    }
    private void initView(){
        setTitle("Create Category");
        btnCreate = findViewById(R.id.btnCreateFormCategory);
        editName = findViewById(R.id.editCategoryName);
        editNameKh = findViewById(R.id.editCategoryNameKh);
        progressBar = findViewById(R.id.progressBarFormCategory);
        progressBar.setVisibility(View.GONE);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        Category item = new Category();

        Intent intent = getIntent();
        Integer id=intent.getIntExtra("ID",0);
        if(id != 0 ){
            setTitle("Update Category");
            btnCreate.setText("Updated");
            progressBar.setVisibility(View.VISIBLE);
            apiInterface.getCategoryById(id).enqueue(new Callback<Category>() {
                @Override
                public void onResponse(Call<Category> call, Response<Category> response) {
                    if(response.isSuccessful()){
                        editName.setText(response.body().getName().toString());
                        editNameKh.setText(response.body().getNameKh().toString());
                        item.setId(response.body().getId());
                        item.setName(response.body().getName());
                        item.setNameKh(response.body().getNameKh());
                        item.setStatus(response.body().getStatus());
                    }
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<Category> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String nameKh   = editNameKh.getText().toString();
                if(name.equals("")){
                    showToastMessage("Category Name is required");
                    return;
                }
                if(nameKh.equals("")){
                    showToastMessage("Category NameKh is required");
                    return;
                }
                item.setStatus("ACT");
                item.setName(name);
                item.setNameKh(nameKh);
                progressBar.setVisibility(View.VISIBLE);
                if(item.getId()!=0){
                    apiInterface.updateCategory(item).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            progressBar.setVisibility(View.GONE);
                            if(response.isSuccessful()){
                                showToastMessage("Update Success");
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            showToastMessage(t.getMessage());
                        }
                    });
                }else {
                    apiInterface.createCategory(item).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            progressBar.setVisibility(View.GONE);
                            if (response.isSuccessful()) {
                                showToastMessage("Create Success");
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            showToastMessage(t.getMessage());
                        }
                    });
                }
            }
        });
    }
}