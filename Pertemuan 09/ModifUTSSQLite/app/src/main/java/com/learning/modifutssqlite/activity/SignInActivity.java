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

public class SignInActivity extends AppCompatActivity {

    private EditText userET, passET;
    private TextView signUpLink;
    private Button signInBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();

        userET = findViewById(R.id.userET);
        passET = findViewById(R.id.passET);
        signUpLink = findViewById(R.id.signUpLink);
        signInBtn = findViewById(R.id.signInBtn);
        cancelBtn = findViewById(R.id.cancelBtn);

        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Berhasil masuk",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userET.setText("");
                passET.setText("");
            }
        });
    }

//        if ((username != null && password != null) &&
//                (username.equals(user) && password.equals(pass))) {
//            Toast.makeText(getApplicationContext(), "Berhasil masuk",
//                    Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(SigninActivity.this, MainActivity.class);
//            startActivity(intent);
//        } else {
//            Toast.makeText(getApplicationContext(),
//                    "Akun tidak ada atau Username & Password tidak cocok...!!",
//                    Toast.LENGTH_SHORT).show();
//        }

}