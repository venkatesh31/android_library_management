package com.example.library.activity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBar;

import com.example.library.R;
import com.example.library.apicalls.BookApiCall;
import com.example.library.libraryenum.EBookCategory;
import com.example.library.request.BookRequest;
import com.example.library.response.Author;
import com.example.library.response.AuthorResponse;
import com.example.library.response.Publisher;
import com.example.library.response.PublisherResponse;
import com.google.android.material.textfield.TextInputEditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("NonConstantResourceId")
@Fullscreen
@EActivity(R.layout.activity_add_publication)
public class AddPublisherActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = AddPublisherActivity.class.getName();

    @ViewById(R.id.name)
    TextInputEditText name;

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
        actionBar.setTitle(getString(R.string.add_publication));
        setClickListeners();
    }


    private void setClickListeners() {
        saveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveButton:
                BookApiCall.addPublication(name.getText().toString(),this);
                break;
        }
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
