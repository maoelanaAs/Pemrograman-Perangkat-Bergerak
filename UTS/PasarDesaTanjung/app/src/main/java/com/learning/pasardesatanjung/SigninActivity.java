package com.learning.pasardesatanjung;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SigninActivity extends AppCompatActivity {

    private EditText inUser, inPass;
    private String user, username, pass, password;
    TextView signupLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        getSupportActionBar().hide();

        inUser = findViewById(R.id.inUser);
        inPass = findViewById(R.id.inPass);
        signupLink = findViewById(R.id.signupLink);

        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onSignin(View view) {

        user = inUser.getText().toString();
        pass = inPass.getText().toString();

        SharedPreferences prefer = getSharedPreferences("DataUser", MODE_PRIVATE);
        username = prefer.getString("user", null);
        password = prefer.getString("pass", null);

        if ((username != null && password != null) &&
                (username.equals(user) && password.equals(pass))) {
            Toast.makeText(getApplicationContext(), "Berhasil masuk",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SigninActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (username == null && password == null) {
            Toast.makeText(getApplicationContext(), "Belum ada akun!!",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Username & Password tidak cocok!!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void onCancel(View view) {
        inUser.setText("");
        inPass.setText("");
    }
}