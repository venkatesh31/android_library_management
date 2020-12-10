package com.example.library.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.library.activity.Application;
import com.example.library.response.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LibraryUtils {

    public static User loginedUser = new User();

    public static void checkAndSetImage(final ImageView imageView, final TextView textView, String imageUrl, final String displayText) {
        if (isValidString(imageUrl)) {
            Glide.with(Application.getContext()).asBitmap().load(imageUrl).into(imageView);

            textView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            textView.setText(displayText.substring(0, 1).toUpperCase());
        }
    }

    public static boolean isValidString(String text) {
        return text != null && !text.isEmpty();
    }


    public static void exitApplication(Activity activity) {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(a);
    }


    public static void showToast(String message) {
        try {
            final Context context = Application.getContext();
            final CharSequence text = message;
            final int duration = Toast.LENGTH_SHORT;
            Handler mainHandler = new Handler(context.getMainLooper());
            Runnable myRunnable = () -> {
                Toast toast = Toast.makeText(context, "  " + text + "  ", duration);
                toast.show();
            };
            mainHandler.post(myRunnable);
        } catch (Exception e) {

        }

    }
}
