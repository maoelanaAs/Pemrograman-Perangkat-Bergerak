package com.learning.pasardesatanjung;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailMenuActivity extends AppCompatActivity {

    ImageView imgDetail;
    TextView nameDetail, descDetail, priceDetail;

    Integer menuImg;
    int menuPrice;
    String menuName, menuDesc;
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance
            (new Locale("in", "ID"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);

        imgDetail = findViewById(R.id.imgDetail);
        descDetail = findViewById(R.id.descDetail);
        nameDetail = findViewById(R.id.nameDetail);
        priceDetail = findViewById(R.id.priceDetail);

        menuImg = getIntent().getIntExtra("img", 0);
        menuDesc = getIntent().getStringExtra("desc");
        menuName = getIntent().getStringExtra("name");
        menuPrice = getIntent().getIntExtra("price", 0);

        imgDetail.setImageResource(Integer.parseInt(String.valueOf(menuImg)));
        descDetail.setText(menuDesc);
        nameDetail.setText(menuName);
        priceDetail.setText(formatRupiah.format(menuPrice));
    }
}