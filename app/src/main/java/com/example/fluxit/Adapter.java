package com.example.fluxit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.fluxit.Model.Results;
import com.example.fluxit.Model.User;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<User> userList;
    private Context context;
    private OnItemClickListener onItemClickListener;


    public Adapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_box, parent, false);
        return new ViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder1, int position) {

        final ViewHolder holder = holder1;
        User user = userList.get(position);



        Glide.with(context)
                .load(user.getPicture().getThumbnail())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.userThumbnail);

        holder.userName.setText(user.getEmail());
    }



    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick (View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView userName;
        ImageView userThumbnail;
        OnItemClickListener onItemClickListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {

            super(itemView);

            itemView.setOnClickListener(this);

            userName = itemView.findViewById(R.id.userNameMainRecycler);
            userThumbnail = itemView.findViewById(R.id.userThumbnailMainRecycler);

            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View view) {

            onItemClickListener.onItemClick(view, getAdapterPosition());

        }
    }
}
