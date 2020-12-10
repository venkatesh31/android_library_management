package com.example.library.adapter.book;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.activity.AddAuthorActivity_;
import com.example.library.apicalls.UserApiCall;
import com.example.library.libraryenum.EStatus;
import com.example.library.request.AddUserBookRequest;
import com.example.library.response.Book;
import com.example.library.utils.LibraryUtils;

import java.util.List;


public class NormalBookAdapter extends RecyclerView.Adapter<NormalBookViewHolder>{

    private static final String TAG = NormalBookAdapter.class.getName();
    private Activity activity;

    private List<Book> bookList;

    public NormalBookAdapter(Activity activity, List<Book> bookList) {
        this.activity = activity;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public NormalBookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_normal_book, parent, false);
        return new NormalBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NormalBookViewHolder holder, int position) {
        try {

            Book book = bookList.get(position);
            holder.name.setText(book.getName());
            LibraryUtils.checkAndSetImage(holder.userImg,holder.userIcon,book.getImageUrl(),book.getName());
            holder.author.setText(book.getAuthor().getName());
            holder.publication.setText(book.getPublisher().getName());
            boolean showBtn = book.getIsAvailable().equals(EStatus.INACTIVE.getCode());
            holder.lendBtn.setVisibility(showBtn ?View.VISIBLE:View.GONE);
            holder.lendBtn.setOnClickListener(v -> {
                AddUserBookRequest addUserBookRequest = new AddUserBookRequest();
                addUserBookRequest.setBookId(book.getBookId());
                addUserBookRequest.setUserId(LibraryUtils.loginedUser.getUserId());
                UserApiCall.lendBook(addUserBookRequest,activity);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return bookList == null ? 0 : bookList.isEmpty() ? 0 : bookList.size();
    }
}