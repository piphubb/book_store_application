package com.piphub.freepostapp_w9.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.piphub.freepostapp_w9.ui.auth.models.User;

public class UserSharePreference {
    private static SharedPreferences sharedPreferences;
    private static String MY_USER = "MY_USER";

    public static void saveUser(User user, Context context, String accessToken) {
        sharedPreferences = context.getSharedPreferences(MY_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("USERNAME", user.getUsername());
        edit.putString("PASSWORD", user.getPassword());
        edit.putString("ACCESS_TOKEN", accessToken);
        edit.apply();
    }

    public static User getUser(Context context) {
        User user = new User();
        sharedPreferences = context.getSharedPreferences(MY_USER, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("USERNAME", "");
        String password = sharedPreferences.getString("PASSWORD", "");
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", "");
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

    public static String getAccessToken(Context context) {
        User user = new User();
        sharedPreferences = context.getSharedPreferences(MY_USER, Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", "");
        return "Bearer " + accessToken;
    }

    public static void clearUser(Context context) {
        sharedPreferences = context.getSharedPreferences(MY_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("USERNAME", "");
        editor.putString("PASSWORD", "");
        editor.putString("ACCESS_TOKEN", "");
        editor.apply();
    }
}
