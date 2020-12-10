package com.example.library.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.library.R;
import com.example.library.apicalls.UserApiCall;
import com.example.library.fragment.AuthorFragment;
import com.example.library.fragment.AuthorFragment_;
import com.example.library.fragment.BookFragment_;
import com.example.library.fragment.PublicationFragment;
import com.example.library.fragment.PublicationFragment_;
import com.example.library.fragment.UserFragment;
import com.example.library.fragment.UserFragment_;
import com.example.library.request.LoginRequest;
import com.example.library.response.AuthorResponse;
import com.example.library.response.BookResponse;
import com.example.library.response.PublisherResponse;
import com.example.library.response.UserListResponse;
import com.example.library.response.UserResponse;
import com.example.library.utils.LibraryUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

@SuppressLint("NonConstantResourceId")
@Fullscreen
@EActivity(R.layout.activity_landing)
public class LandingActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private static final String TAG = LandingActivity.class.getName();



    @ViewById(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    boolean isBackPressed = false;

    ActionBar actionBar;

    @AfterViews
    public void init() {
         actionBar =getSupportActionBar();

        Fragment selectedFragment = new BookFragment_();
        loadFragment(selectedFragment, "Book");
        //BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
       setBottomNavigation();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }

    }

    private void setBottomNavigation() {

        Menu menu = bottomNavigationView.getMenu();
        menu.add(Menu.NONE, R.string.book, Menu.NONE, getString(R.string.book));
        menu.add(Menu.NONE, R.string.author, Menu.NONE, getString(R.string.author));
        menu.add(Menu.NONE, R.string.publication, Menu.NONE, getString(R.string.publication));
        menu.add(Menu.NONE, R.string.user, Menu.NONE, getString(R.string.user));

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        String tag = "";


        switch (item.getItemId()) {
            case R.string.book:
                selectedFragment = new BookFragment_();
                tag = getString(R.string.book);
                break;
            case R.string.author:
                selectedFragment = new AuthorFragment_();
                tag =  getString(R.string.author);
                Log.d(TAG, "fragment 2");
                break;
            case R.string.publication:
                selectedFragment = new PublicationFragment_();
                tag =  getString(R.string.publication);
                Log.d(TAG, "fragment 3");
                break;
            case R.string.user:
                selectedFragment = new UserFragment_();
                tag =  getString(R.string.user);
                Log.d(TAG, "fragment 4");
                break;

        }
        Log.d(TAG, tag);

        return loadFragment(selectedFragment, tag);
    }

    private boolean loadFragment(Fragment fragment, String tag) {
        actionBar.setTitle(tag);
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, fragment, tag)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (isBackPressed) {
            LibraryUtils.exitApplication(this);
        } else {
            isBackPressed = true;
            LibraryUtils.showToast("Press again to exit");
            (new Handler()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    isBackPressed = false;
                }
            }, 10000);
        }
    }

    public void setBookResponse(BookResponse bookResponse) {
        BookFragment_ bookFragment_ = (BookFragment_) getSupportFragmentManager().findFragmentByTag(getString(R.string.book));
        bookFragment_.setBookResponse(bookResponse);
    }

    public void setPublisherResponse(PublisherResponse publisherResponse) {
        PublicationFragment_ publicationFragment_ = (PublicationFragment_) getSupportFragmentManager().findFragmentByTag(getString(R.string.publication));
        publicationFragment_.setPublicationResponse(publisherResponse);
    }
    public void setAuthorResponse(AuthorResponse authorResponse) {
        AuthorFragment_ authorFragment_ = (AuthorFragment_) getSupportFragmentManager().findFragmentByTag(getString(R.string.author));
        authorFragment_.setAuthorResponse(authorResponse);
    }
    public void setUserResponse(UserListResponse userListResponse) {
        UserFragment_ userFragment_ = (UserFragment_) getSupportFragmentManager().findFragmentByTag(getString(R.string.user));
        userFragment_.setUserResponse(userListResponse);
    }

}
