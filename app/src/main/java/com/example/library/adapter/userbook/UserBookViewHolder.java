package com.example.library.adapter.userbook;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;


public class UserBookViewHolder extends RecyclerView.ViewHolder {

    TextView name,userIcon,publication,author;
    CardView cardItem;
    Button returnBtn;
    ImageView userImg;

    public UserBookViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        publication = itemView.findViewById(R.id.publication);
        author = itemView.findViewById(R.id.author);
        userIcon =  itemView.findViewById(R.id.userIcon);
        cardItem =  itemView.findViewById(R.id.cardItem);
        returnBtn =  itemView.findViewById(R.id.returnBtn);
        userImg =  itemView.findViewById(R.id.userImg);

    }
}
