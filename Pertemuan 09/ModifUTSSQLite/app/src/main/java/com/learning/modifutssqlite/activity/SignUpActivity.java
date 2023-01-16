package com.learning.modifutssqlite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.learning.modifutssqlite.MainActivity;
import com.learning.modifutssqlite.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText nameET, userET, passET;
    private TextView signInLink;
    private Button signUpBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        nameET = findViewById(R.id.nameET);
        userET = findViewById(R.id.userET);
        passET = findViewById(R.id.passET);
        signInLink = findViewById(R.id.signInLink);
        signUpBtn = findViewById(R.id.signUpBtn);
        cancelBtn = findViewById(R.id.cancelBtn);

        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = nameET.getText().toString();
                String newUser = userET.getText().toString();
                String newPass = passET.getText().toString();

                Toast.makeText(getApplicationContext(), "Berhasil daftar",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameET.setText("");
                userET.setText("");
                passET.setText("");
            }
        });
    }
}