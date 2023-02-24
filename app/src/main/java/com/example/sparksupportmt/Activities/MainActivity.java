package com.example.sparksupportmt.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sparksupportmt.Adapter.ImageAdapter;
import com.example.sparksupportmt.ViewModel.ImageViewModel;
import com.example.sparksupportmt.R;
import com.example.sparksupportmt.Model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageAdapter adapter;

    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageViewModel model = ViewModelProviders.of(this).get(ImageViewModel.class);
        model.getImage().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> userList) {
                adapter = new ImageAdapter(MainActivity.this, userList);
                recyclerView.setAdapter(adapter);

            }
        });

    }
}