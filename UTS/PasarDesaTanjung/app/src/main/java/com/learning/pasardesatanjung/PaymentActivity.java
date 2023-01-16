package com.learning.pasardesatanjung;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class PaymentActivity extends AppCompatActivity {

    private TextView totalPrice, change;
    private EditText totalPayment;
    int TPrice, Change, TPayment;
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance
            (new Locale("in", "ID"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        totalPrice = findViewById(R.id.totalPrice);
        totalPayment = findViewById(R.id.totalPayment);
        change = findViewById(R.id.change);

        TPrice = getIntent().getIntExtra("total_price", 0);
        totalPrice.setText(formatRupiah.format(TPrice));

        totalPayment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (totalPayment.getText().toString().isEmpty()) {
                    TPayment = TPrice;
                } else {
                    TPayment = Integer.parseInt(totalPayment.getText().toString());
                }
                Change = TPayment - TPrice;
                change.setText(formatRupiah.format(Change));
            }
        });
    }
}