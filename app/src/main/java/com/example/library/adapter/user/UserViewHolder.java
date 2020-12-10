package com.example.library.adapter.user;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;


public class UserViewHolder extends RecyclerView.ViewHolder {

    TextView name,userIcon;
    CardView cardItem;
    ImageView deleteIcon;

    public UserViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        userIcon =  itemView.findViewById(R.id.userIcon);
        cardItem =  itemView.findViewById(R.id.cardItem);
        deleteIcon =  itemView.findViewById(R.id.deleteIcon);

    }
}
