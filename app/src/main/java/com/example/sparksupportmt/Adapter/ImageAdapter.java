package com.example.sparksupportmt.Adapter;

import android.content.Context;
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
    Context mCtx;
    List<User> imageList;
    public ImageAdapter(Context mCtx, List<User> imageList) {
        this.mCtx = mCtx;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate( R.layout.recycle_layout, parent, true);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        User hero = imageList.get(position);

        Glide.with(mCtx).load("http://192.168.0.100/api/images/"+imageList.get(position).getImage()).into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        if(this.imageList!=null)
            return this.imageList.size();
        else
            return 0;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
