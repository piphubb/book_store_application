package com.piphub.freepostapp_w9.ui.dashboard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.piphub.freepostapp_w9.MainActivity;
import com.piphub.freepostapp_w9.R;
import com.piphub.freepostapp_w9.adapter.ProductAdapter;
import com.piphub.freepostapp_w9.apis.APIClient;
import com.piphub.freepostapp_w9.apis.APIInterface;
import com.piphub.freepostapp_w9.app.BaseBackButtonActivity;
import com.piphub.freepostapp_w9.data.local.UserSharePreference;
import com.piphub.freepostapp_w9.model.Product;
import com.piphub.freepostapp_w9.ui.auth.LoginActivity;
import com.piphub.freepostapp_w9.ui.category.CategoryActivity;
import com.piphub.freepostapp_w9.ui.product.FormProductActivity;
import com.piphub.freepostapp_w9.ui.product.ProductActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends BaseBackButtonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }
    public void openCategoryActivity(View view){
        Intent intent = new Intent(DashboardActivity.this, CategoryActivity.class);
        startActivity(intent);
    }
    public void openProductActivity(View view){
        Intent intent = new Intent(DashboardActivity.this, ProductActivity.class);
        startActivity(intent);
    }
    public void onClickLogout(View view){
        UserSharePreference.clearUser(this);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onOpenFormProductActivity(View view){
        Intent intent = new Intent(DashboardActivity.this, FormProductActivity.class);
        startActivity(intent);
    }
}