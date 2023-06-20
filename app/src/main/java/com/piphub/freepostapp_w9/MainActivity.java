package com.piphub.freepostapp_w9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.piphub.freepostapp_w9.apis.APIInterface;
import com.piphub.freepostapp_w9.app.BaseActivity;
import com.piphub.freepostapp_w9.data.local.UserSharePreference;
import com.piphub.freepostapp_w9.ui.auth.LoginActivity;
import com.piphub.freepostapp_w9.ui.category.CategoryActivity;
import com.piphub.freepostapp_w9.ui.product.ProductActivity;

public class MainActivity extends BaseActivity {
    private APIInterface apiInterface;
    private Button btnOpenCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOpenCategory = findViewById(R.id.btnListData);
        btnOpenCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

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
    public void openProductActivity(View view){
        Intent intent = new Intent(MainActivity.this, ProductActivity.class);
        startActivity(intent);
    }
    public void onClickLogout(View view){
        UserSharePreference.clearUser(this);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}