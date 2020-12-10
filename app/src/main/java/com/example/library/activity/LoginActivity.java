package com.example.library.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;

import com.cloudinary.android.MediaManager;
import com.example.library.R;
import com.example.library.apicalls.UserApiCall;
import com.example.library.libraryenum.EUserType;
import com.example.library.request.LoginRequest;
import com.example.library.response.UserResponse;
import com.example.library.utils.LibraryUtils;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity{

    @Override
    protected void onStart() {
        super.onStart();
        Map config = new HashMap();
        config.put("cloud_name", "duduhjpuv");
        config.put("api_key", "156546374791465");
        config.put("api_secret", "afuICiNrSyW0ssgQW9DWx5reVYA");
        MediaManager.init(this, config);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle("Login");
        setLayoutValue();
    }

   void setLayoutValue(){
       TextInputEditText userNameText =(TextInputEditText) findViewById(R.id.username);
       TextInputEditText passwordText =(TextInputEditText) findViewById(R.id.password);

      Button loginBtn = (Button)findViewById(R.id.login);
       loginBtn.setOnClickListener(v -> {
           String username = userNameText.getText()==null?"":userNameText.getText().toString().trim();
           String password = passwordText.getText()==null?"":passwordText.getText().toString().trim();

           onLoginClick(username,password);
       });
   }

   void onLoginClick(String username,String password){
       if(isValidString(username)){
           LibraryUtils.showToast("Please Enter Valid Username");
           return;
       }
       if(isValidString(password)){
           LibraryUtils.showToast("Please Enter Valid Password");
           return;
       }
       UserApiCall.login(loginRequest(username,password),LoginActivity.this);
   }

   public void setLoginResponse(UserResponse userResponse){
        if(userResponse==null || userResponse.getRecordinfo()==null){
            LibraryUtils.showToast("Invalid Username/Password");
            return;
        }
       LibraryUtils.showToast("Login Sucessfully");
        LibraryUtils.loginedUser = userResponse.getRecordinfo();
        if(LibraryUtils.loginedUser.getUserType().equals(EUserType.ADMIN.getCode())){
            Intent nextScreen = new Intent(this, LandingActivity_.class);
            startActivity(nextScreen);
        }else{
            Intent nextScreen = new Intent(this, UserLandingActivity_.class);
            startActivity(nextScreen);
        }

       return;

   }

   LoginRequest loginRequest(String username,String password){
       LoginRequest loginRequest = new LoginRequest();
       loginRequest.setUserName(username);
       loginRequest.setPassword(password);
       return loginRequest;
   }

   boolean isValidString(String text){
        return text == null || text.isEmpty();
   }
}
