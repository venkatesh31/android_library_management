package com.example.library.adapter.author;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;


public class AuthorViewHolder extends RecyclerView.ViewHolder {

    TextView name,userIcon;
    CardView cardItem;

    public AuthorViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        userIcon =  itemView.findViewById(R.id.userIcon);
        cardItem =  itemView.findViewById(R.id.cardItem);

    }
}
