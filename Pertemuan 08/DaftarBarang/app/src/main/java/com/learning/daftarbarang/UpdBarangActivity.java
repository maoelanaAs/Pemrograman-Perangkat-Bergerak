package com.learning.daftarbarang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdBarangActivity extends AppCompatActivity {

    EditText kodeBrgET2, namaBrgET2, hrgBeliET2, hrgJualET2, stokBrgET2;
    Button btnUpdate;

    String kode_brg, nama_brg, hrg_beli, hrg_jual, stok_brg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upd_barang);

        kodeBrgET2 = findViewById(R.id.kodeBrgET2);
        namaBrgET2 = findViewById(R.id.namaBrgET2);
        hrgBeliET2 = findViewById(R.id.hrgBeliET2);
        hrgJualET2 = findViewById(R.id.hrgJualET2);
        stokBrgET2 = findViewById(R.id.stokBrgET2);
        btnUpdate = findViewById(R.id.btnUpdate);

        getAndSetIntentExtra();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BarangDatabaseHelper dbHelper = new BarangDatabaseHelper(UpdBarangActivity.this);

                dbHelper.updBarang(
                        kodeBrgET2.getText().toString().trim(),
                        namaBrgET2.getText().toString().trim(),
                        Integer.parseInt(hrgBeliET2.getText().toString().trim()),
                        Integer.parseInt(hrgJualET2.getText().toString().trim()),
                        Integer.parseInt(stokBrgET2.getText().toString().trim())
                );

                Intent intent = new Intent(UpdBarangActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    void getAndSetIntentExtra() {
        if (getIntent().hasExtra("kodeBrg") &&
                getIntent().hasExtra("namaBrg") &&
                getIntent().hasExtra("hrgBeli") &&
                getIntent().hasExtra("hrgJual") &&
                getIntent().hasExtra("stokBrg")) {

            kode_brg = getIntent().getStringExtra("kodeBrg");
            nama_brg = getIntent().getStringExtra("namaBrg");
            hrg_beli = getIntent().getStringExtra("hrgBeli");
            hrg_jual = getIntent().getStringExtra("hrgJual");
            stok_brg = getIntent().getStringExtra("stokBrg");

            kodeBrgET2.setText(kode_brg);
            namaBrgET2.setText(nama_brg);
            hrgBeliET2.setText(hrg_beli);
            hrgJualET2.setText(hrg_jual);
            stokBrgET2.setText(stok_brg);
        } else {
            Toast.makeText(this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        }
    }
}