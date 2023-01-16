package com.learning.modifutsmysql.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.learning.modifutsmysql.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText nameuserET, usernameET, passwordET;
    private String newName, newUser, newPass;
    private Button signUpBtn, cancelBtn;
    private TextView signInLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        nameuserET = findViewById(R.id.nameuserET);
        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
        signUpBtn = findViewById(R.id.signUpBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        signInLink = findViewById(R.id.signInLink);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences prefer = getSharedPreferences("dataUser", MODE_PRIVATE);

                newName = nameuserET.getText().toString();
                newUser = usernameET.getText().toString();
                newPass = passwordET.getText().toString();

                SharedPreferences.Editor edit = prefer.edit();
                edit.putString("name", newName);
                edit.putString("user", newUser);
                edit.putString("pass", newPass);
                edit.putString(newUser + newPass + "data", newUser + "\n" + newName);
                edit.apply();

                Toast.makeText(getApplicationContext(), "Berhasil daftar",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameuserET.setText("");
                usernameET.setText("");
                passwordET.setText("");
            }
        });


        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}