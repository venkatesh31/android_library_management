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
import com.example.library.activity.AddAuthorActivity;
import com.example.library.activity.AddAuthorActivity_;
import com.example.library.activity.AddBookActivity_;
import com.example.library.adapter.author.AuthorAdapter;
import com.example.library.apicalls.BookApiCall;
import com.example.library.request.SearchRequest;
import com.example.library.response.AuthorResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


@SuppressLint("NonConstantResourceId")
@EFragment(R.layout.fragment_author)
public class AuthorFragment extends Fragment {

    private static final String TAG = AuthorFragment.class.getName();

    @ViewById(R.id.addAuthor)
    Button addAuthor;


    @ViewById(R.id.authorList)
    RecyclerView authorList;

    @AfterViews
    public void init() {
        Log.d(TAG, "Init called");
        setClickListener();
    }


    public void setAuthorResponse(AuthorResponse authorResponse) {

        if (authorResponse == null || authorResponse.getData() == null || authorResponse.getData().isEmpty() ) {
            authorList.setVisibility(View.GONE);
            return;
        }
        authorList.setVisibility(View.VISIBLE);
        authorList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        authorList.setLayoutManager(linearLayoutManager);
        AuthorAdapter authorAdapter = new AuthorAdapter(getActivity(), authorResponse.getData());
        authorList.setAdapter(authorAdapter);
    }

    private void setClickListener() {
        addAuthor.setOnClickListener(v -> {
            Intent nextScreen = new Intent(getActivity(), AddAuthorActivity_.class);
            startActivity(nextScreen);
        });


    }


    @Override
    public void onResume() {
        super.onResume();
        BookApiCall.allAuthor(getActivity());
    }

}
