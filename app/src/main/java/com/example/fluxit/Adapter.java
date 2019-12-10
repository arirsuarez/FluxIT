package com.example.fluxit;

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
import com.example.fluxit.model.pojo.User;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.UserViewHolder> {

    private List<User> userList;
    private BoxListener boxListener;

    public Adapter(List<User> userList, BoxListener boxListener) {
        this.userList = userList;
        this.boxListener = boxListener;

    }

    public void refreshRecyclerList(List<User> userList) {
        this.userList.addAll(userList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_box, parent, false);
        UserViewHolder viewHolder = new UserViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.bind(user);
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private ImageView userThumbnailBox;
        private TextView userNameBox;

        public UserViewHolder(@NonNull final View itemView) {
            super(itemView);

            userNameBox = itemView.findViewById(R.id.userNameMainRecycler);
            userThumbnailBox = itemView.findViewById(R.id.userThumbnailMainRecycler);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    User user = userList.get(getAdapterPosition());
                    boxListener.userPicked(user);

                }
            });
        }

        public void bind (User user){

            Glide.with(itemView)
                    .load(user.getPicture().getLarge())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(userThumbnailBox);

            userNameBox.setText(user.getLogin().getUsername());

        }
    }

    public interface BoxListener{
        public void userPicked(User userPicked);

    }

   /* public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView userName;
        ImageView userThumbnail;
        AdapterView.OnItemClickListener onItemClickListener;

        public ViewHolder(@NonNull View itemView, AdapterView.OnItemClickListener onItemClickListener) {

            super(itemView);

            // itemView.setOnClickListener(this);

            userName = itemView.findViewById(R.id.userNameMainRecycler);
            userThumbnail = itemView.findViewById(R.id.userThumbnailMainRecycler);

            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

       @Override
        public void onClick(View view) {

        }

        public void bind(User user) {
            Glide.with(itemView)
                    .load(user.getPicture().getLarge())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(userThumbnail);

            userName.setText(user.getLogin().getUsername());
        }

    }*/
}
