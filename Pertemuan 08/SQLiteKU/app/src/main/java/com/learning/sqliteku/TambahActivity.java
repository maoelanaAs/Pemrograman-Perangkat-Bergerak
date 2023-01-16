package com.learning.sqliteku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class TambahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        final DatabaseHandler db = new DatabaseHandler(this);
        final EditText editNis = findViewById(R.id.editNis);
        final EditText editNama = findViewById(R.id.editNama);
        Button btnTambah = findViewById(R.id.btnTambah);
        Button btnBatal = findViewById(R.id.btnBatal);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nis = editNis.getText().toString();
                String nama = editNama.getText().toString();

                db.addSiswa(new Siswa(nis, nama));

                editNis.setText("");
                editNama.setText("");

                try {
                    Class c = Class.forName("com.learning.sqliteku.MainActivity");

                    Intent i = new Intent(TambahActivity.this, c);
                    startActivity(i);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Class c = Class.forName("com.learning.sqliteku.MainActivity");

                    Intent i = new Intent(TambahActivity.this, c);
                    startActivity(i);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}