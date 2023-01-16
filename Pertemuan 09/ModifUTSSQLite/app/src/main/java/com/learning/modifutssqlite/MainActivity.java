package com.learning.modifutssqlite;

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
import android.widget.TextView;
import android.widget.Toast;

import com.learning.modifutssqlite.activity.PaymentActivity;
import com.learning.modifutssqlite.adapter.RecyclerAdapter;
import com.learning.modifutssqlite.model.ProductModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ProductModel> data;
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
                Intent iUpUser = new Intent(this, MainActivity.class);
                startActivity(iUpUser);
                return true;
            case R.id.action_insert_product:
                Toast.makeText(getApplicationContext(), "Insert Product",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_update_product:
                Toast.makeText(getApplicationContext(), "Update Product",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_delete_product:
                Toast.makeText(getApplicationContext(), "Delete Product",
                        Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateTotal() {
        TextView total_price = (TextView) findViewById(R.id.totalPrice);
        total_price.setText("Total : " + formatRupiah.format(TPrice));
    }
}