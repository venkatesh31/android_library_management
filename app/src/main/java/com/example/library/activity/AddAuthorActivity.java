package com.example.library.activity;


import android.annotation.SuppressLint;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;

import com.example.library.R;
import com.example.library.apicalls.BookApiCall;
import com.google.android.material.textfield.TextInputEditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;


@SuppressLint("NonConstantResourceId")
@Fullscreen
@EActivity(R.layout.activity_add_author)
public class AddAuthorActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = AddAuthorActivity.class.getName();

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
        actionBar.setTitle(getString(R.string.add_author));
        setClickListeners();
    }


    private void setClickListeners() {
        saveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveButton:
                BookApiCall.addAuthor(name.getText().toString(),this);
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
