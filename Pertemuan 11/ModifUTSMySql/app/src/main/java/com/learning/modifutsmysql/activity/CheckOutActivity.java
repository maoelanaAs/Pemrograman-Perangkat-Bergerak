package com.learning.modifutsmysql.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.learning.modifutsmysql.MainActivity;
import com.learning.modifutsmysql.R;

import java.text.NumberFormat;
import java.util.Locale;

public class CheckOutActivity extends AppCompatActivity {

    private TextView totalPrice, change;
    private EditText totalPayment;
    private Button backBtn;
    Double TPrice, Change, TPayment;
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance
            (new Locale("in", "ID"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        totalPrice = findViewById(R.id.totalPrice);
        totalPayment = findViewById(R.id.totalPayment);
        change = findViewById(R.id.change);
        backBtn = findViewById(R.id.backBtn);

        TPrice = getIntent().getDoubleExtra("total_price", 0);
        totalPrice.setText(formatRupiah.format(TPrice));

        totalPayment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (totalPayment.getText().toString().isEmpty()) {
                    TPayment = TPrice;
                } else {
                    TPayment = Double.parseDouble(totalPayment.getText().toString());
                }
                Change = TPayment - TPrice;
                change.setText(formatRupiah.format(Change));
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckOutActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}