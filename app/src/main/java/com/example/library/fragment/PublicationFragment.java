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
import com.example.library.activity.AddBookActivity_;
import com.example.library.activity.AddPublisherActivity;
import com.example.library.activity.AddPublisherActivity_;
import com.example.library.adapter.author.AuthorAdapter;
import com.example.library.adapter.publication.PublicationAdapter;
import com.example.library.apicalls.BookApiCall;
import com.example.library.request.SearchRequest;
import com.example.library.response.AuthorResponse;
import com.example.library.response.PublisherResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


@SuppressLint("NonConstantResourceId")
@EFragment(R.layout.fragment_publication)
public class PublicationFragment extends Fragment {

    private static final String TAG = PublicationFragment.class.getName();

    @ViewById(R.id.addPublication)
    Button addPublication;


    @ViewById(R.id.publicationList)
    RecyclerView publicationList;

    @AfterViews
    public void init() {
        Log.d(TAG, "Init called");
        setClickListener();
    }


    public void setPublicationResponse(PublisherResponse publisherResponse) {

        if (publisherResponse == null || publisherResponse.getData() == null || publisherResponse.getData().isEmpty() ) {
            publicationList.setVisibility(View.GONE);
            return;
        }
        publicationList.setVisibility(View.VISIBLE);
        publicationList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        publicationList.setLayoutManager(linearLayoutManager);
        PublicationAdapter authorAdapter = new PublicationAdapter(getActivity(), publisherResponse.getData());
        publicationList.setAdapter(authorAdapter);
    }

    private void setClickListener() {
        addPublication.setOnClickListener(v -> {
            Intent nextScreen = new Intent(getActivity(), AddPublisherActivity_.class);
            startActivity(nextScreen);
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        BookApiCall.allPublisher(getActivity());
    }

}
