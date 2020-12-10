package com.example.library.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.activity.AddUserActivity_;
import com.example.library.adapter.user.UserAdapter;
import com.example.library.apicalls.BookApiCall;
import com.example.library.apicalls.UserApiCall;
import com.example.library.response.UserListResponse;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


@SuppressLint("NonConstantResourceId")
@EFragment(R.layout.fragment_user)
public class UserFragment extends Fragment {

    private static final String TAG = UserFragment.class.getName();

    @ViewById(R.id.addUser)
    Button addUser;


    @ViewById(R.id.userList)
    RecyclerView userList;

    @AfterViews
    public void init() {
        Log.d(TAG, "Init called");
        setClickListener();
    }


    public void setUserResponse(UserListResponse userListResponse) {

        if (userListResponse == null || userListResponse.getData() == null || userListResponse.getData().isEmpty() ) {
            userList.setVisibility(View.GONE);
            return;
        }
        userList.setVisibility(View.VISIBLE);
        userList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        userList.setLayoutManager(linearLayoutManager);
        UserAdapter authorAdapter = new UserAdapter(getActivity(), userListResponse.getData());
        userList.setAdapter(authorAdapter);
    }

    private void setClickListener() {
        addUser.setOnClickListener(v -> {
            Intent nextScreen = new Intent(getActivity(), AddUserActivity_.class);
            startActivity(nextScreen);
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        UserApiCall.getNormalUser(getActivity());
    }

}
