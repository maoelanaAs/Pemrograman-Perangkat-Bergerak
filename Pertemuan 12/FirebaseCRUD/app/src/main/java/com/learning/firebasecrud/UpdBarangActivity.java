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

public class UpdBarangActivity extends AppCompatActivity {

    EditText kodeBrgET2, namaBrgET2, hrgBeliET2, hrgJualET2, stanBrgET2, stokBrgET2, stokMinET2;
    Button btnUpdate;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upd_barang);

        kodeBrgET2 = findViewById(R.id.kodeBrgET2);
        namaBrgET2 = findViewById(R.id.namaBrgET2);
        hrgBeliET2 = findViewById(R.id.hrgBeliET2);
        hrgJualET2 = findViewById(R.id.hrgJualET2);
        stanBrgET2 = findViewById(R.id.stanBrgET2);
        stokBrgET2 = findViewById(R.id.stokBrgET2);
        stokMinET2 = findViewById(R.id.stokMinET2);
        btnUpdate = findViewById(R.id.btnUpdate);

        getAndSetIntentExtra();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getKodeBrg = kodeBrgET2.getText().toString();
                String getNamaBrg = namaBrgET2.getText().toString();
                String getHrgBeli = hrgBeliET2.getText().toString();
                String getHrgJual = hrgJualET2.getText().toString();
                String getStanBrg = stanBrgET2.getText().toString();
                String getStokBrg = stokBrgET2.getText().toString();
                String getStokMin = stokMinET2.getText().toString();
                String getKey = getIntent().getStringExtra("KEY");

                database.child("barang").child(getKey).setValue(new ModelBarang
                                (getKodeBrg, getNamaBrg, getHrgBeli, getHrgJual,
                                        getStanBrg, getStokBrg, getStokMin))
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(UpdBarangActivity.this,
                                        "Data berhasil diupdate!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(UpdBarangActivity.this, MainActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UpdBarangActivity.this,
                                        "Data gagal diupdate!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    void getAndSetIntentExtra() {

        String getKodeBrg = getIntent().getStringExtra("kodeBrg");
        String getNamaBrg = getIntent().getStringExtra("namaBrg");
        String getHrgBeli = getIntent().getStringExtra("hrgBeli");
        String getHrgJual = getIntent().getStringExtra("hrgJual");
        String getStanBrg = getIntent().getStringExtra("stanBrg");
        String getStokBrg = getIntent().getStringExtra("stokBrg");
        String getStokMin = getIntent().getStringExtra("stokMin");

        kodeBrgET2.setText(getKodeBrg);
        namaBrgET2.setText(getNamaBrg);
        hrgBeliET2.setText(getHrgBeli);
        hrgJualET2.setText(getHrgJual);
        stanBrgET2.setText(getStanBrg);
        stokBrgET2.setText(getStokBrg);
        stokMinET2.setText(getStokMin);
    }
}