package com.learning.daftarbarang;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addBtn;

    BarangDatabaseHelper dbHelper;
    ArrayList<String> kode_brg, nama_brg, hrg_beli, hrg_jual, stok_brg;
    BarangAdapter barangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addBtn = findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InsBarangActivity.class);
                startActivity(intent);
            }
        });

        dbHelper = new BarangDatabaseHelper(MainActivity.this);
        kode_brg = new ArrayList<>();
        nama_brg = new ArrayList<>();
        hrg_beli = new ArrayList<>();
        hrg_jual = new ArrayList<>();
        stok_brg = new ArrayList<>();

        getDataToArray();

        barangAdapter = new BarangAdapter(MainActivity.this, this,
                kode_brg, nama_brg, hrg_beli, hrg_jual, stok_brg);
        recyclerView.setAdapter(barangAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            recreate();
        }
    }

    void getDataToArray() {
        Cursor cursor = dbHelper.getAllBarang();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Tidak Ada Barang", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                kode_brg.add(cursor.getString(0));
                nama_brg.add(cursor.getString(1));
                hrg_beli.add(cursor.getString(2));
                hrg_jual.add(cursor.getString(3));
                stok_brg.add(cursor.getString(4));
            }
        }
    }
}