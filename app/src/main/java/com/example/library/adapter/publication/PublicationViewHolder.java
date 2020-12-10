package com.example.library.adapter.publication;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;


public class PublicationViewHolder extends RecyclerView.ViewHolder {

    TextView name,userIcon;
    CardView cardItem;

    public PublicationViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        userIcon =  itemView.findViewById(R.id.userIcon);
        cardItem =  itemView.findViewById(R.id.cardItem);

    }
}
