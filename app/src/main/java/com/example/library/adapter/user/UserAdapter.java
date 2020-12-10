package com.example.library.adapter.user;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.activity.AddBookActivity_;
import com.example.library.apicalls.UserApiCall;
import com.example.library.response.User;

import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{

    private static final String TAG = UserAdapter.class.getName();
    private Activity activity;

    private List<User> userList;

    public UserAdapter(Activity activity, List<User> userList) {
        this.activity = activity;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        try {

            User user = userList.get(position);
            holder.name.setText(user.getName());
            holder.userIcon.setText(user.getName().substring(0,1));
            holder.deleteIcon.setOnClickListener(v -> {
                UserApiCall.deleteUser(user.getUserId(),activity);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 : userList.isEmpty() ? 0 : userList.size();
    }
}