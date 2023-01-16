package com.learning.serasamenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.ItemClickListener {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<MenuModel> data;
    int TPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<>();
        for (int i = 0; i < Menu.menuName.length; i++) {
            data.add(new MenuModel(
                    Menu.menuName[i],
                    Menu.menuImg[i],
                    Menu.menuPrice[i]
            ));
        }

        recyclerAdapter = new RecyclerAdapter(data);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setClickListener(this);
    }

    public void updateTotal()
    {
        TextView total_price=(TextView) findViewById(R.id.totalPrice);
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance
                (new Locale("in", "ID"));
        total_price.setText("Total : " + formatRupiah.format(TPrice));
    }


    @Override
    public void onClick(View view, int position) {
        MenuModel menu = data.get(position);
        switch (view.getId()){
            case R.id.menuImg:
                TPrice = TPrice + menu.getMenuPrice();
                updateTotal();
                break;
            default:
                break;
        }
    }
}