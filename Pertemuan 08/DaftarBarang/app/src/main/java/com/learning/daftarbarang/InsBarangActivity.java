package com.learning.daftarbarang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsBarangActivity extends AppCompatActivity {

    EditText kodeBrgET, namaBrgET, hrgBeliET, hrgJualET, stokBrgET;
    Button btnTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_barang);

        kodeBrgET = findViewById(R.id.kodeBrgET);
        namaBrgET = findViewById(R.id.namaBrgET);
        hrgBeliET = findViewById(R.id.hrgBeliET);
        hrgJualET = findViewById(R.id.hrgJualET);
        stokBrgET = findViewById(R.id.stokBrgET);
        btnTambah = findViewById(R.id.btnTambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BarangDatabaseHelper dbHelper = new BarangDatabaseHelper(InsBarangActivity.this);

                dbHelper.insBarang(
                        kodeBrgET.getText().toString().trim(),
                        namaBrgET.getText().toString().trim(),
                        Integer.parseInt(hrgBeliET.getText().toString().trim()),
                        Integer.parseInt(hrgJualET.getText().toString().trim()),
                        Integer.parseInt(stokBrgET.getText().toString().trim())
                );

                Intent intent = new Intent(InsBarangActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}