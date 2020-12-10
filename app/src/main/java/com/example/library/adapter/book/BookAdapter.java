package com.example.library.adapter.book;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.activity.AddBookActivity_;
import com.example.library.response.Book;
import com.example.library.utils.LibraryUtils;

import java.util.List;


public class BookAdapter extends RecyclerView.Adapter<BookViewHolder>{

    private static final String TAG = BookAdapter.class.getName();
    private Activity activity;

    private List<Book> bookList;

    public BookAdapter(Activity activity, List<Book> bookList) {
        this.activity = activity;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        try {

            Book book = bookList.get(position);
            holder.name.setText(book.getName());
            LibraryUtils.checkAndSetImage(holder.userImg,holder.userIcon,book.getImageUrl(),book.getName());
            holder.author.setText(book.getAuthor().getName());
            holder.publication.setText(book.getPublisher().getName());
            holder.editIcon.setOnClickListener(v -> {
                Intent nextScreen = new Intent(activity, AddBookActivity_.class);
                nextScreen.putExtra("bookItem", book);
                activity.startActivity(nextScreen);
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