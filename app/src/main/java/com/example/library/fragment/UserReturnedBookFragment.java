package com.example.library.fragment;


import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.adapter.userbook.UserBookAdapter;
import com.example.library.apicalls.UserApiCall;
import com.example.library.libraryenum.EStatus;
import com.example.library.response.UserBookResponse;
import com.example.library.utils.LibraryUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


@SuppressLint("NonConstantResourceId")
@EFragment(R.layout.fragment_user_book)
public class UserReturnedBookFragment extends Fragment {

    private static final String TAG = UserReturnedBookFragment.class.getName();


    @ViewById(R.id.bookList)
    RecyclerView bookList;

    @AfterViews
    public void init() {
        Log.d(TAG, "Init called");
        setClickListener();
    }


    public void setUserBookResponse(UserBookResponse authorResponse) {

        if (authorResponse == null || authorResponse.getData() == null || authorResponse.getData().isEmpty() ) {
            bookList.setVisibility(View.GONE);
            return;
        }
        bookList.setVisibility(View.VISIBLE);
        bookList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        bookList.setLayoutManager(linearLayoutManager);
        UserBookAdapter authorAdapter = new UserBookAdapter(getActivity(), authorResponse.getData());
        bookList.setAdapter(authorAdapter);
    }

    private void setClickListener() {



    }


    @Override
    public void onResume() {
        super.onResume();
        UserApiCall.getUserBook(LibraryUtils.loginedUser.getUserId(), EStatus.INACTIVE.getCode(),getActivity());
    }

}
