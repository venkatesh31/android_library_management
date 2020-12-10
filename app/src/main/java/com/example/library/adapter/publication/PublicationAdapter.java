package com.example.library.adapter.publication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.response.Publisher;

import java.util.List;


public class PublicationAdapter extends RecyclerView.Adapter<PublicationViewHolder>{

    private static final String TAG = PublicationAdapter.class.getName();
    private Activity activity;

    private List<Publisher> publisherList;

    public PublicationAdapter(Activity activity, List<Publisher> publisherList) {
        this.activity = activity;
        this.publisherList = publisherList;
    }

    @NonNull
    @Override
    public PublicationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_author, parent, false);
        return new PublicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicationViewHolder holder, int position) {
        try {

            Publisher publisher = publisherList.get(position);
            holder.name.setText(publisher.getName());
            holder.userIcon.setText(publisher.getName().substring(0,1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return publisherList == null ? 0 : publisherList.isEmpty() ? 0 : publisherList.size();
    }
}