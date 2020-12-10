package com.example.library.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.activity.AddBookActivity_;
import com.example.library.activity.BaseActivity;
import com.example.library.adapter.book.BookAdapter;
import com.example.library.apicalls.BookApiCall;
import com.example.library.libraryenum.EStatus;
import com.example.library.request.SearchRequest;
import com.example.library.response.Book;
import com.example.library.response.BookResponse;
import com.example.library.utils.LibraryUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;


@SuppressLint("NonConstantResourceId")
@EFragment(R.layout.fragment_book)
public class BookFragment extends Fragment {

    private static final String TAG = BookFragment.class.getName();

    @ViewById(R.id.addBook)
    Button addBook;


    @ViewById(R.id.searchEditText)
    TextInputEditText searchEditText;

    @ViewById(R.id.searchButton)
    ImageButton searchButton;


    @ViewById(R.id.bookList)
    RecyclerView bookList;

    @ViewById(R.id.chart)
    PieChartView pieChartView;

    @AfterViews
    public void init() {
        Log.d(TAG, "Init called");
        setClickListener();
    }


    public void setBookResponse(BookResponse bookResponse) {

        if (bookResponse == null || bookResponse.getData() == null || bookResponse.getData().isEmpty() ) {
            bookList.setVisibility(View.GONE);
            return;
        }
        setChartData(bookResponse.getData());
        bookList.setVisibility(View.VISIBLE);
        bookList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        bookList.setLayoutManager(linearLayoutManager);
        BookAdapter parishAdapter = new BookAdapter(getActivity(), bookResponse.getData());
        bookList.setAdapter(parishAdapter);
    }

    private void setClickListener() {
        addBook.setOnClickListener(v -> {
            Intent nextScreen = new Intent(getActivity(), AddBookActivity_.class);
            startActivity(nextScreen);
        });
        searchButton.setOnClickListener(v -> {
            if(searchEditText.getText()==null || searchEditText.getText().toString()==null || searchEditText.getText().toString().isEmpty()){
                BookApiCall.search(searchRequest(""),getActivity());
                return;
            }
            BookApiCall.search(searchRequest(searchEditText.getText().toString()),getActivity());
        });


    }

    SearchRequest searchRequest(String searchString){
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setSearchText(searchString);
        return searchRequest;
    }

    @Override
    public void onResume() {
        super.onResume();
        searchEditText.getText().clear();
        BookApiCall.search(searchRequest(""),getActivity());
    }

    void setChartData(List<Book> bookList){
        int lendedBooks=0;
        int remainingBooks=0;
        for(Book book:bookList){
            if(book.getIsAvailable().equals(EStatus.INACTIVE.getCode())){
                remainingBooks++;
            }else{
                lendedBooks++;
            }
        }
        List<SliceValue> pieData = new ArrayList<>();
        pieData.add(new SliceValue(lendedBooks, Color.BLUE).setLabel(getActivity().getString(R.string.lend)));
        pieData.add(new SliceValue(remainingBooks, Color.GREEN).setLabel(getActivity().getString(R.string.available)));
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true);
        pieChartView.setPieChartData(pieChartData);

    }

}
