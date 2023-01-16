package com.learning.pasardesatanjung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateUserActivity extends AppCompatActivity {

    private EditText inUser, inPass;
    private String name, user, username, pass, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        inUser = findViewById(R.id.inUser);
        inPass = findViewById(R.id.inPass);

        SharedPreferences prefer = getSharedPreferences("DataUser", MODE_PRIVATE);
        name = prefer.getString("name", null);
        username = prefer.getString("user", null);
        password = prefer.getString("pass", null);

        inUser.setText(username);
        inPass.setText(password);
    }

    public void onUpdate(View view) {


        user = inUser.getText().toString();
        pass = inPass.getText().toString();

        SharedPreferences prefer = getSharedPreferences("DataUser", MODE_PRIVATE);
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

    public void onCancel(View view) {
        inUser.setText("");
        inPass.setText("");
    }
}