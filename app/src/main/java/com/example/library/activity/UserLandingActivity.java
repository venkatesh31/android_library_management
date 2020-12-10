package com.example.library.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.example.library.R;
import com.example.library.fragment.AuthorFragment_;
import com.example.library.fragment.BookFragment_;
import com.example.library.fragment.NormalBookFragment_;
import com.example.library.fragment.PublicationFragment_;
import com.example.library.fragment.UserBookFragment_;
import com.example.library.fragment.UserFragment_;
import com.example.library.fragment.UserReturnedBookFragment;
import com.example.library.fragment.UserReturnedBookFragment_;
import com.example.library.response.AuthorResponse;
import com.example.library.response.BookResponse;
import com.example.library.response.PublisherResponse;
import com.example.library.response.UserBookResponse;
import com.example.library.response.UserListResponse;
import com.example.library.utils.LibraryUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

@SuppressLint("NonConstantResourceId")
@Fullscreen
@EActivity(R.layout.activity_landing)
public class UserLandingActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private static final String TAG = UserLandingActivity.class.getName();



    @ViewById(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    boolean isBackPressed = false;

    ActionBar actionBar;

    @AfterViews
    public void init() {
         actionBar =getSupportActionBar();

        Fragment selectedFragment = new NormalBookFragment_();
        loadFragment(selectedFragment,  getString(R.string.book));
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
        menu.add(Menu.NONE, R.string.current, Menu.NONE, getString(R.string.current));
        menu.add(Menu.NONE, R.string.returned, Menu.NONE, getString(R.string.returned));

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        String tag = "";


        switch (item.getItemId()) {
            case R.string.book:
                selectedFragment = new NormalBookFragment_();
                tag = getString(R.string.book);
                break;
            case R.string.current:
                selectedFragment = new UserBookFragment_();
                tag =  getString(R.string.current);
                Log.d(TAG, "fragment 2");
                break;
            case R.string.returned:
                selectedFragment = new UserReturnedBookFragment_();
                tag =  getString(R.string.returned);
                Log.d(TAG, "fragment 3");
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
        NormalBookFragment_ bookFragment_ = (NormalBookFragment_) getSupportFragmentManager().findFragmentByTag(getString(R.string.book));
        bookFragment_.setBookResponse(bookResponse);
    }


    public void setCurrentBook(UserBookResponse userBookResponse) {
        UserBookFragment_ authorFragment_ = (UserBookFragment_) getSupportFragmentManager().findFragmentByTag(getString(R.string.current));
        authorFragment_.setUserBookResponse(userBookResponse);
    }
    public void setReturnedBook(UserBookResponse userBookResponse) {
        UserReturnedBookFragment_ userFragment_ = (UserReturnedBookFragment_) getSupportFragmentManager().findFragmentByTag(getString(R.string.returned));
        userFragment_.setUserBookResponse(userBookResponse);
    }

}
