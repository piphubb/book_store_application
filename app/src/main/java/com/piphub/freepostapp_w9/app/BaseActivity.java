package com.piphub.freepostapp_w9.app;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.piphub.freepostapp_w9.data.local.UserSharePreference;
import com.piphub.freepostapp_w9.ui.auth.LoginActivity;


public class BaseActivity extends AppCompatActivity {
    private Boolean loged = false;
    @Override
    protected void onResume() {
        super.onResume();
        if(UserSharePreference.getUser(this).getUsername().equals("")){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
