package com.example.library.adapter.userbook;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.apicalls.UserApiCall;
import com.example.library.libraryenum.EStatus;
import com.example.library.request.AddUserBookRequest;
import com.example.library.response.Book;
import com.example.library.response.UserBook;
import com.example.library.utils.LibraryUtils;

import java.util.List;


public class UserBookAdapter extends RecyclerView.Adapter<UserBookViewHolder>{

    private static final String TAG = UserBookAdapter.class.getName();
    private Activity activity;

    private List<UserBook> userBookList;

    public UserBookAdapter(Activity activity, List<UserBook> userBookList) {
        this.activity = activity;
        this.userBookList = userBookList;
    }

    @NonNull
    @Override
    public UserBookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_user_book, parent, false);
        return new UserBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserBookViewHolder holder, int position) {
        try {

            UserBook userBook = userBookList.get(position);
            Book book = userBook.getBook();
            holder.name.setText(book.getName());
            LibraryUtils.checkAndSetImage(holder.userImg,holder.userIcon,book.getImageUrl(),book.getName());
            holder.author.setText(book.getAuthor().getName());
            holder.publication.setText(book.getPublisher().getName());
            boolean showBtn = userBook.getIsBookReturned().equals(EStatus.ACTIVE.getCode());
            holder.returnBtn.setVisibility(showBtn ?View.VISIBLE:View.GONE);
            holder.returnBtn.setOnClickListener(v -> {
                UserApiCall.returnBook(userBook.getUserBookId(),activity);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return userBookList == null ? 0 : userBookList.isEmpty() ? 0 : userBookList.size();
    }
}