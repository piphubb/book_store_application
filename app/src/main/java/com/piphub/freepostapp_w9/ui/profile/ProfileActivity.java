package com.piphub.freepostapp_w9.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.piphub.freepostapp_w9.MainActivity;
import com.piphub.freepostapp_w9.R;
import com.piphub.freepostapp_w9.app.BaseBackButtonActivity;
import com.piphub.freepostapp_w9.ui.dashboard.DashboardActivity;

public class ProfileActivity extends BaseBackButtonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
   public void onClickAdminDashboard(View view){
       Intent intent = new Intent(ProfileActivity.this, DashboardActivity.class);
       startActivity(intent);
   }
}