package com.example.library.activity;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity{
    private String TAG = BaseActivity.this.getClass().getName();
    public ProgressDialog mprogressdialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeProgressDialog();
    }


    public void initializeProgressDialog() {
        mprogressdialog = new ProgressDialog(this);
        mprogressdialog.setCanceledOnTouchOutside(false);
        mprogressdialog.setCancelable(false);
    }

    public void showProgressDialog(String message) {
        mprogressdialog.setMessage(message);
        mprogressdialog.show();
    }

    public void hideProgressDialog() {
        if (mprogressdialog.isShowing()) {
            mprogressdialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancelAll();
    }
}
