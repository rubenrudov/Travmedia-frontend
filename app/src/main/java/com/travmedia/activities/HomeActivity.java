package com.travmedia.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.travmedia.R;
import com.travmedia.communication.SocketTask;
import com.travmedia.handlers.PostAdapter;
import com.travmedia.models.Post;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;
    List<Post> posts;
    PostAdapter postAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Objects.requireNonNull(getSupportActionBar()).setTitle("All posts"); // TODO: Add searching/filter options in V 1.1
        bottomNavigationView = findViewById(R.id.home_menu);
        bottomNavigationView.setSelectedItemId(R.id.home_activity);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_activity) {
                    startActivity(new Intent(HomeActivity.this, NavigatorActivity.class));
                    finish();
                    return true;
                }

                else if (item.getItemId() == R.id.upload_activity) {
                    Intent i = new Intent(HomeActivity.this, UploadPostActivity.class);
                    i.putExtra("username", HomeActivity.this.getIntent().getStringExtra("username"));
                    startActivity(i);
                    finish();
                    return true;
                }

                // If selected the current page (HomeActivity)
                else return item.getItemId() == R.id.home_activity;
            }
        });

        //posts = getPosts();

        posts = new ArrayList<>();

        posts.add(new Post("Ruben Rudov", "My trip to Napoli, Italy", "Hey, in this post I'll tell you about my last trip to Naples, Italy and the Amalfi coast",
                "21:48 - 23/12/2019")
        );
        posts.add(new Post("Ruben Rudov", "My trip to Florance, Italy", "Hey, in this post I'll tell you about my last trip to Florance and Toscana, Italy"
                , "21:48 - 23/12/2019")
        );
        posts.add(new Post("Ruben Rudov", "My trip to Vienna, Austria", "Hey, in this post I'll tell you about my last trip to Vienna and Semmering, Austria"
                ,"21:48 - 23/12/2019")
        );
        posts.add(new Post("Ruben Rudov", "My trip to Vienna, Austria", "Hey, in this post I'll tell you about my last trip to Vienna and Semmering, Austria"
                ,"21:48 - 23/12/2019")
        );
        posts.add(new Post("Ruben Rudov", "My trip to Vienna, Austria", "Hey, in this post I'll tell you about my last trip to Vienna and Semmering, Austria"
                ,"21:48 - 23/12/2019")
        );
        postAdapter = new PostAdapter(HomeActivity.this, posts);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(postAdapter);
    }

    /* public ArrayList<Post> getPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        JSONObject data = new JSONObject();
        try {
            data.put("request", "get_all_posts");
            JSONObject receivedData;
            SocketTask getPosts = new SocketTask(data);
            receivedData = getPosts.execute().get();
            JSONObject child;
            int i = 0;
            while (true){
                child = receivedData.getJSONObject(String.valueOf(i));
                if (child.equals("finished")) {
                    posts.add(new Post(
                            child.getString("publisher"),
                            child.getString("title"),
                            child.getString("content"),
                            child.getString("publishing_date")
                    ));
                } else {
                    break; // Finishes the work of posts getter
                }
                i++;
            }
        } catch (InterruptedException | JSONException | ExecutionException e) {
            e.printStackTrace();
        }
        return posts;
    } */

    void refreshAdapter(){
        // After every update of the posts list, the adapter will be refreshed
        postAdapter = new PostAdapter(HomeActivity.this, posts);
        recyclerView.setAdapter(postAdapter);
    }


}
