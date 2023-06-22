package com.piphub.freepostapp_w9.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.piphub.freepostapp_w9.MainActivity;
import com.piphub.freepostapp_w9.R;
import com.piphub.freepostapp_w9.apis.APIClient;
import com.piphub.freepostapp_w9.apis.APIInterface;
import com.piphub.freepostapp_w9.data.local.UserSharePreference;
import com.piphub.freepostapp_w9.ui.auth.models.LoginResponse;
import com.piphub.freepostapp_w9.ui.auth.models.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText username, password;
    private ProgressBar progressBar;
    private APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        initView();
        progressBar.setVisibility(View.GONE);
    }
    private void initView() {
        username = findViewById(R.id.edUsername);
        password = findViewById(R.id.edPassword);
        progressBar = findViewById(R.id.progressBar);
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }
    public void onClickLogin(View view) {
        if (username.getText().toString().equals("")) {
            showToastMessage("Username is required");
            return;
        }

        if (password.getText().toString().equals("")) {
            showToastMessage("Password is required");
            return;
        }

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPhoneNumber(username.getText().toString().trim());
        loginRequest.setPassword(password.getText().toString().trim());
        progressBar.setVisibility(View.VISIBLE);
        apiInterface.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.code() == 401) {
                    showToastMessage("Your username and password incorrect!");
                }
                if (response.isSuccessful()) {
                    UserSharePreference.saveUser(response.body().getUser(),LoginActivity.this,response.body().getAccessToken());
                    showToastMessage("You login success!");
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                showToastMessage("ERROR : " + t.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });
    }
    protected void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}