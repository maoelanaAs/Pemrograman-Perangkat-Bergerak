package com.learning.firebasecrud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addBtn;
    AdapterBarang adapterBarang;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<ModelBarang> listBarang;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn = findViewById(R.id.addBtn);
        recyclerView = findViewById(R.id.recyclerView);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentIns = new Intent(MainActivity.this, InsBarangActivity.class);
                startActivity(intentIns);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        displayBrg();
    }

    private void displayBrg() {
        database.child("barang").orderByChild("kode_brg").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listBarang = new ArrayList<>();

                for (DataSnapshot item : snapshot.getChildren()) {
                    ModelBarang brg = item.getValue(ModelBarang.class);
                    brg.setKey(item.getKey());

                    listBarang.add(brg);
                }

                adapterBarang = new AdapterBarang(listBarang, MainActivity.this);

                recyclerView.setAdapter(adapterBarang);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}