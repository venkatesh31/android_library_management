package com.example.library.adapter.book;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;


public class BookViewHolder extends RecyclerView.ViewHolder {

    TextView name,userIcon,publication,author;
    CardView cardItem;
    ImageView userImg,editIcon;

    public BookViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        publication = itemView.findViewById(R.id.publication);
        author = itemView.findViewById(R.id.author);
        userIcon =  itemView.findViewById(R.id.userIcon);
        cardItem =  itemView.findViewById(R.id.cardItem);
        userImg =  itemView.findViewById(R.id.userImg);
        editIcon =  itemView.findViewById(R.id.editIcon);

    }
}
