package com.learning.modifutsmysql.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.learning.modifutsmysql.MainActivity;
import com.learning.modifutsmysql.R;

public class SignInActivity extends AppCompatActivity {

    private EditText usernameET, passwordET;
    private String user, username, pass, password;
    private Button signInBtn, cancelBtn;
    private TextView signUpLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();

        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
        signInBtn = findViewById(R.id.signInBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        signUpLink = findViewById(R.id.signUpLink);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user = usernameET.getText().toString();
                pass = passwordET.getText().toString();

                SharedPreferences prefer = getSharedPreferences("dataUser", MODE_PRIVATE);
                username = prefer.getString("user", null);
                password = prefer.getString("pass", null);

                if ((username != null && password != null) &&
                        (username.equals(user) && password.equals(pass))) {
                    Toast.makeText(getApplicationContext(), "Berhasil masuk",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(intent);
                } else if (username == null && password == null) {
                    Toast.makeText(getApplicationContext(), "Belum ada akun!!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Username & Password tidak cocok!!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usernameET.setText("");
                passwordET.setText("");
            }
        });

        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}