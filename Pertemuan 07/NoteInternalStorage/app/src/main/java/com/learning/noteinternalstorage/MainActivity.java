package com.learning.noteinternalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtNotes;

    private SharedPreferences mPreferences;
    private static final String mSharedPrefFile = "com.learning.noteinternalstorage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNotes = findViewById(R.id.txtNotes);
        mPreferences = getSharedPreferences(mSharedPrefFile, Activity.MODE_PRIVATE);
    }

    public void save(View view) {

        SharedPreferences.Editor editor = mPreferences.edit();

        editor.putString("notes", txtNotes.getText().toString());
        editor.commit();
        alert();
    }

    public void read(View view) {

        txtNotes.setText(mPreferences.getString("notes", null));
    }

    private void alert() {

        AlertDialog.Builder alrt = new AlertDialog.Builder(this);
        alrt.setTitle("Konfirmasi");
        alrt.setMessage("Data Tersimpan");
        alrt.setIcon(R.drawable.ic_alert);
        alrt.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(getApplicationContext(), "Berhasil Menyimpan",
                        Toast.LENGTH_SHORT).show();
            }
        });
        alrt.show();
    }
}