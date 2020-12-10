package com.example.library.activity;


import android.annotation.SuppressLint;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;

import com.example.library.R;
import com.example.library.apicalls.BookApiCall;
import com.example.library.apicalls.UserApiCall;
import com.example.library.request.AddUserRequest;
import com.google.android.material.textfield.TextInputEditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;


@SuppressLint("NonConstantResourceId")
@Fullscreen
@EActivity(R.layout.activity_add_user)
public class AddUserActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = AddUserActivity.class.getName();

    @ViewById(R.id.name)
    TextInputEditText name;

    @ViewById(R.id.username)
    TextInputEditText username;

    @ViewById(R.id.password)
    TextInputEditText password;

    @ViewById(R.id.saveButton)
    Button saveButton;
    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @AfterViews
    public void init() {
        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle(getString(R.string.add_user));
        setClickListeners();
    }


    private void setClickListeners() {
        saveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveButton:
                UserApiCall.addUser(addUserRequest(),this);
                break;
        }
    }

    AddUserRequest addUserRequest(){
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setUserName(username.getText().toString());
        addUserRequest.setName(name.getText().toString());
        addUserRequest.setPassword(password.getText().toString());
        return addUserRequest;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
