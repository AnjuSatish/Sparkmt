package com.example.sparksupportmt.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sparksupportmt.Model.User;
import com.example.sparksupportmt.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private List<User> images;
    private Context context;

    public ImageAdapter(Context context, List<User> images) {
        this.context = context;
        this.images = images;
    }


    @NonNull
    @Override
    public ImageAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_layout, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ImageViewHolder holder, int position) {
        User user = images.get(position);

        Glide.with(context).load(user.getImage_link()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        if(this.images!=null)
            return this.images.size();
        else
            return 0;
    }



    public class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super( itemView );
            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}
