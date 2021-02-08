package com.travmedia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.travmedia.R;
import com.travmedia.communication.SocketTask;
import com.travmedia.handlers.BackgroundHandling;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button submit;
    BackgroundHandling backgroundHandling = new BackgroundHandling();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();
        ImageView background = findViewById(R.id.background);
        String backgroundPic = backgroundHandling.getRandomPicture();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        // Glide.with(this).load(backgroundPic).override(width + 101, height + 100).centerCrop().into(background);
        username = findViewById(R.id.usernameEt);
        password = findViewById(R.id.passwordEt);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String uname  = username.getText().toString();
                boolean b = login(uname);
                if(b) {
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    i.putExtra("username", uname);
                    startActivity(i);
                    finish();
                }
            }
        });
    }


    public boolean login(String query) {
        JSONObject data = new JSONObject();
        try {
            data.put("request", "get_password");
            data.put("username", query);
            SocketTask registerTask = new SocketTask(data);
            JSONObject receivedData = registerTask.execute().get();

            if (receivedData.getString("response").equals(password.getText().toString()))
                return true;
        } catch (JSONException | InterruptedException | ExecutionException exception) {
            Log.d("Exception", exception.toString());
        }

        Log.d("Result problem", "Data wasn't inserted into the database ");
        return false;
    }
}
