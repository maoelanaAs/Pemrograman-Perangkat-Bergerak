package com.learning.pasardesatanjung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    private EditText inName, inUser, inPass;
    TextView signinLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        inName = findViewById(R.id.inName);
        inUser = findViewById(R.id.inUser);
        inPass = findViewById(R.id.inPass);
        signinLink = findViewById(R.id.signinLink);

        signinLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onSignup(View view) {
        SharedPreferences prefer = getSharedPreferences("DataUser", MODE_PRIVATE);

        String newName = inName.getText().toString();
        String newUser = inUser.getText().toString();
        String newPass = inPass.getText().toString();

        SharedPreferences.Editor edit = prefer.edit();
        edit.putString("name", newName);
        edit.putString("user", newUser);
        edit.putString("pass", newPass);
        edit.putString(newUser + newPass + "data", newUser + "\n" + newName);
        edit.apply();

        Toast.makeText(getApplicationContext(), "Berhasil daftar",
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
        startActivity(intent);
    }

    public void onCancel(View view) {
        inName.setText("");
        inUser.setText("");
        inPass.setText("");
    }
}