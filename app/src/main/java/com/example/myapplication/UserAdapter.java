package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private ArrayList<User> userList;
    private Context mContext;
    private static final String TAG = "demo";
    //Constructor for UserAdapter...
    public UserAdapter(ArrayList<User> userList, Context mContext) {
        this.userList = userList;
        this.mContext = mContext;
    }

    // This method creates views for the RecyclerView by inflating the layout
    // Into the viewHolders which helps to display the items in the RecyclerView
    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.cell, parent, false);

        return new UserHolder(view);
    }

    // This method is called when binding the data to the views being created in RecyclerView
    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User user = userList.get(position);

        // Set the data to the views here
        holder.setUserName(user.getName());
        holder.setUserImage(user.getImageURL());

        Log.d(TAG, "onBindViewHolder: "+user.toString());

        // You can set click listners to indvidual items in the viewholder here
        // make sure you pass down the listner or make the Data members of the viewHolder public
    }

    @Override
    public int getItemCount() {
        return userList == null? 0: userList.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private ImageView iv_image;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_cell);
            iv_image = itemView.findViewById(R.id.iv_cell);
        }

        public void setUserName(String name){
            tv_name.setText(name);
        }

        public void setUserImage(String url){
            Picasso.get().load(url).into(iv_image);
        }
    }
}
