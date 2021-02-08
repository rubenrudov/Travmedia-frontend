package com.travmedia.activities;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.travmedia.R;
import com.travmedia.communication.SocketTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class UploadPostActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    public EditText title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_post);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Upload new post");

        title = findViewById(R.id.title);
        content = findViewById(R.id.content);

        bottomNavigationView = findViewById(R.id.upload_page_menu);
        bottomNavigationView.setSelectedItemId(R.id.upload_activity);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home_activity) {
                    startActivity(new Intent(UploadPostActivity.this, HomeActivity.class));
                    finish();
                    return true;
                }
                else if (item.getItemId() == R.id.navigation_activity) {
                        startActivity(new Intent(UploadPostActivity.this, NavigatorActivity.class));
                    finish();
                        return true;
                }
                // If selected the current page (NavigatorActivity)
                else return item.getItemId() == R.id.navigation_activity;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.done_button, menu);
        return true;
    }


    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO: Upload post function
        final String title1 = UploadPostActivity.this.title.getText().toString();
        final String content1 = UploadPostActivity.this.content.getText().toString();
        final String publisher = UploadPostActivity.this.getIntent().getStringExtra("username");
        AlertDialog.Builder builder = new AlertDialog.Builder(UploadPostActivity.this);
        View viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.before_upload_dialog, (ViewGroup) viewGroup, false);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        dialogView.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            // Approves the post, inserts it into the database (via the server), returns back to Home page and closes this activity.
            @Override
            public void onClick(View v) {
                uploadPost(title1, content1, publisher);
                startActivity(new Intent(UploadPostActivity.this, HomeActivity.class));
                finish();
            }
        });

        dialogView.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            // Takes the user back to continue his post
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        return true;
    }

    public void uploadPost(String title, String content, String username){
        JSONObject data = new JSONObject();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MM/yyyy', 'HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        try {
            data.put("request", "add_post");
            data.put("publisher", username);
            data.put("title", title);
            data.put("content", content);
            data.put("publishing_date", simpleDateFormat.format(date));
            SocketTask uploadPost = new SocketTask(data);
            JSONObject receivedData = uploadPost.execute().get();

            if (receivedData.getString("response").equals("Successful")) {
                Toast.makeText(this, "Post upload was successful", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException | InterruptedException | ExecutionException exception) {
            Log.d("Exception", exception.toString());
        }
    }
}
