package com.learning.luaskeliling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PersegiActivity extends AppCompatActivity {

    EditText panjang, lebar;
    TextView luas, keliling;
    Float pjg, lbr, ls, kl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persegi);

        panjang = findViewById(R.id.panjang);
        lebar = findViewById(R.id.lebar);
        luas = findViewById(R.id.luas);
        keliling = findViewById(R.id.keliling);
    }


    public void onHitung(View view) {
        try {
            pjg = Float.parseFloat(panjang.getText().toString());
            lbr = Float.parseFloat(lebar.getText().toString());

            ls = pjg * lbr;
            kl = 2 * (pjg + lbr);

            luas.setText("Luas = " + Float.toString(ls) + " cm^2");
            keliling.setText("Keliling = " + Float.toString(kl) + " cm");
        } catch (NumberFormatException e) {
            Log.d("Error", "Error Format" + e);
        }
    }

}