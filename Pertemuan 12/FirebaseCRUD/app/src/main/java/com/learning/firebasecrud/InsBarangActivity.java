package com.learning.firebasecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsBarangActivity extends AppCompatActivity {

    EditText kodeBrgET, namaBrgET, hrgBeliET, hrgJualET, stanBrgET, stokBrgET, stokMinET;
    Button btnTambah;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_barang);

        kodeBrgET = findViewById(R.id.kodeBrgET);
        namaBrgET = findViewById(R.id.namaBrgET);
        hrgBeliET = findViewById(R.id.hrgBeliET);
        hrgJualET = findViewById(R.id.hrgJualET);
        stanBrgET = findViewById(R.id.stanBrgET);
        stokBrgET = findViewById(R.id.stokBrgET);
        stokMinET = findViewById(R.id.stokMinET);
        btnTambah = findViewById(R.id.btnTambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getKodeBrg = kodeBrgET.getText().toString();
                String getNamaBrg = namaBrgET.getText().toString();
                String getHrgBeli = hrgBeliET.getText().toString();
                String getHrgJual = hrgJualET.getText().toString();
                String getStanBrg = stanBrgET.getText().toString();
                String getStokBrg = stokBrgET.getText().toString();
                String getStokMin = stokMinET.getText().toString();

                if (getKodeBrg.isEmpty()) {
                    kodeBrgET.setError("Kode Barang tidak boleh kosong!");
                } else {
                    database.child("barang").push().setValue(new ModelBarang
                                    (getKodeBrg, getNamaBrg, getHrgBeli, getHrgJual,
                                            getStanBrg, getStokBrg, getStokMin))
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(InsBarangActivity.this,
                                            "Data berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(InsBarangActivity.this, MainActivity.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(InsBarangActivity.this,
                                            "Data gagal ditambahkan!", Toast.LENGTH_SHORT).show();
                                }
                            });

                }
            }
        });
    }
}