package com.example.library.adapter.book;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;


public class NormalBookViewHolder extends RecyclerView.ViewHolder {

    TextView name,userIcon,publication,author;
    CardView cardItem;
    Button lendBtn;
    ImageView userImg;

    public NormalBookViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        publication = itemView.findViewById(R.id.publication);
        author = itemView.findViewById(R.id.author);
        userIcon =  itemView.findViewById(R.id.userIcon);
        cardItem =  itemView.findViewById(R.id.cardItem);
        lendBtn =  itemView.findViewById(R.id.lendBtn);
        userImg =  itemView.findViewById(R.id.userImg);
    }
}
