package com.learning.pasardesatanjung;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance
            (new Locale("in", "ID"));
    TextView totalPrice;
    int TPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        totalPrice = findViewById(R.id.totalPrice);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<>();
        for (int i = 0; i < Menu.menuName.length; i++) {
            data.add(new MenuModel(
                    Menu.menuName[i],
                    Menu.menuDesc[i],
                    Menu.menuImg[i],
                    Menu.menuPrice[i]
            ));
        }

        recyclerAdapter = new RecyclerAdapter(data);
        recyclerAdapter.setClickListener(this);
        recyclerView.setAdapter(recyclerAdapter);

        totalPrice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PaymentActivity.class);
                intent.putExtra("total_price", TPrice);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_call_center:
                Toast.makeText(getApplicationContext(), "Call Center",
                        Toast.LENGTH_SHORT).show();
                Intent iCall = new Intent(Intent.ACTION_DIAL);
                iCall.setData(Uri.parse("tel:087730393303"));
                if (iCall.resolveActivity(getPackageManager()) != null) {
                    startActivity(iCall);
                }
                return true;
            case R.id.action_sms_center:
                Toast.makeText(getApplicationContext(), "SMS Center",
                        Toast.LENGTH_SHORT).show();
                Intent iSMS = new Intent(Intent.ACTION_SENDTO);
                iSMS.setData(Uri.parse("smsto:087730393303"));
                if (iSMS.resolveActivity(getPackageManager()) != null) {
                    startActivity(iSMS);
                }
                return true;
            case R.id.action_lokasi:
                Toast.makeText(getApplicationContext(), "Lokasi",
                        Toast.LENGTH_SHORT).show();
                Intent iLoc = new Intent(Intent.ACTION_VIEW);
                iLoc.setData(Uri.parse("geo:0,0?q=Kantor+Kelurahan+Tanjung+Klego"));
                if (iLoc.resolveActivity(getPackageManager()) != null) {
                    startActivity(iLoc);
                }
                return true;
            case R.id.action_update_user:
                Toast.makeText(getApplicationContext(), "Update User",
                        Toast.LENGTH_SHORT).show();
                Intent iUpUser = new Intent(this, UpdateUserActivity.class);
                startActivity(iUpUser);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateTotal() {
        TextView total_price = (TextView) findViewById(R.id.totalPrice);
        total_price.setText("Total : " + formatRupiah.format(TPrice));
    }

    @Override
    public void onClick(View view, int position) {
        MenuModel menu = data.get(position);
        switch (view.getId()) {
            case R.id.menuImg:
                Toast.makeText(this, "+ " + formatRupiah.format(menu.getMenuPrice()),
                                Toast.LENGTH_SHORT).show();
                TPrice = TPrice + menu.getMenuPrice();
                updateTotal();
                break;
            case R.id.menuName:
                Toast.makeText(this, menu.getMenuName(), Toast.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(this, DetailMenuActivity.class);
                intent.putExtra("name", menu.getMenuName());
                intent.putExtra("desc", menu.getMenuDesc());
                intent.putExtra("img", menu.getMenuImg());
                intent.putExtra("price", menu.getMenuPrice());
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}