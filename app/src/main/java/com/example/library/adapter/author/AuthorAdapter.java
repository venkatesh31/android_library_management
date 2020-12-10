package com.example.library.adapter.author;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.response.Author;

import java.util.List;


public class AuthorAdapter extends RecyclerView.Adapter<AuthorViewHolder>{

    private static final String TAG = AuthorAdapter.class.getName();
    private Activity activity;

    private List<Author> authorList;

    public AuthorAdapter(Activity activity, List<Author> authorList) {
        this.activity = activity;
        this.authorList = authorList;
    }

    @NonNull
    @Override
    public AuthorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_author, parent, false);
        return new AuthorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorViewHolder holder, int position) {
        try {

            Author author = authorList.get(position);
            holder.name.setText(author.getName());
            holder.userIcon.setText(author.getName().substring(0,1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return authorList == null ? 0 : authorList.isEmpty() ? 0 : authorList.size();
    }
}