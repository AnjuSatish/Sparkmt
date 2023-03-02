package com.example.sparksupportmt.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sparksupportmt.Adapter.ImageAdapter;
import com.example.sparksupportmt.ViewModel.ImageViewModel;
import com.example.sparksupportmt.R;
import com.example.sparksupportmt.Model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageAdapter adapter;
    Button logout;
    private ImageViewModel imageViewModel;

    List<User> userListt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        logout = findViewById(R.id.logout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        imageViewModel = new ViewModelProvider(this).get(ImageViewModel.class);
        imageViewModel.getImagesLiveData().observe(this, images -> {
            recyclerView.setAdapter(new ImageAdapter(this, images));
        });

        imageViewModel.fetchImages();

        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity( i );
                Toast.makeText( MainActivity.this, "Successfully Logged out", Toast.LENGTH_SHORT ).show();
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
            }
        } );


    }
}