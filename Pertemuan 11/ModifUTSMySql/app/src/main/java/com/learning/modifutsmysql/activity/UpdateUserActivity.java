package com.learning.modifutsmysql.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.learning.modifutsmysql.MainActivity;
import com.learning.modifutsmysql.R;

public class UpdateUserActivity extends AppCompatActivity {

    private EditText usernameET, passwordET;
    private String user, username, pass, password;
    Button updateBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
        updateBtn = findViewById(R.id.updateBtn);
        cancelBtn = findViewById(R.id.cancelBtn);

        SharedPreferences prefer = getSharedPreferences("dataUser", MODE_PRIVATE);
        username = prefer.getString("user", null);
        password = prefer.getString("pass", null);

        usernameET.setText(username);
        passwordET.setText(password);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = usernameET.getText().toString();
                pass = passwordET.getText().toString();

                SharedPreferences prefer = getSharedPreferences("dataUser", MODE_PRIVATE);
                SharedPreferences.Editor edit = prefer.edit();
                edit.putString("user", user);
                edit.putString("pass", pass);
                edit.putString(user + pass + "data", user + "\n" + pass);
                edit.apply();

                Toast.makeText(getApplicationContext(), "Berhasil update",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateUserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateUserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}