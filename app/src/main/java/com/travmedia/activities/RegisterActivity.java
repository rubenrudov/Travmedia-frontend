package com.travmedia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.travmedia.R;
import com.travmedia.communication.SocketTask;
import com.travmedia.handlers.BackgroundHandling;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class RegisterActivity extends AppCompatActivity {
    EditText username, email, password;
    Button submit;
    BackgroundHandling backgroundHandling = new BackgroundHandling();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).hide();
        username = findViewById(R.id.usernameEt);
        email = findViewById(R.id.emailEt);
        password = findViewById(R.id.passwordEt);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Register function if the user isn't registered in the data base yet (Use smart validators..)
                // TODO: Validators for username email and password
                if(validate())
                {
                    if(register()) {
                        startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                        finish();
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Failed to insert the data to the database", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(RegisterActivity.this, "more than 2 chars for username, 1 @ and password between 8-16 chars", Toast.LENGTH_SHORT).show();
                }

            }

            private boolean validate() {
                return username.getText().toString().length() > 2 &&
                        email.getText().toString().length() > 8 &&
                        password.getText().toString().length() >= 8 &&
                        password.getText().toString().length() < 16 &&
                        findAmount(email.getText().toString(), '@') == 1 &&
                        findAmount(email.getText().toString(), '.') >= 1;
            }

            private int findAmount(String text, char c) {
                int amount = 0;
                for (char ch: text.toCharArray()){
                    amount += ch == c ? 1 : 0;      // One linear for adding to the char counter
                }
                return amount;
            }
        });
    }

    public boolean register() {
        JSONObject data = new JSONObject();
        try {
            data.put("request", "add_user");
            data.put("username", username.getText().toString());
            data.put("email", email.getText().toString());
            data.put("password", password.getText().toString());
            SocketTask registerTask = new SocketTask(data);
            JSONObject receivedData = registerTask.execute().get();

            if (receivedData.getString("response").equals("Successful"))
                return true;
        } catch (JSONException | InterruptedException | ExecutionException exception) {
            Log.d("Exception", exception.toString());
        }

        Log.d("Result problem", "Data wasn't inserted into the database ");
        return false;
    }
}
