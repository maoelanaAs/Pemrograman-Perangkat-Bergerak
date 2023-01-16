package com.learning.luaskeliling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SegitigaActivity extends AppCompatActivity {

    EditText alas, tinggi, pjgsisi1, pjgsisi2, pjgsisi3;
    TextView luas, keliling;
    Float als, tgg;
    Float pjgss1, pjgss2, pjgss3;
    double ls;
    Float kl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segitiga);

        // Luas
        alas = findViewById(R.id.alas);
        tinggi = findViewById(R.id.tinggi);
        luas = findViewById(R.id.luas);

        // Keliling
        pjgsisi1 = findViewById(R.id.pjgsisi1);
        pjgsisi2 = findViewById(R.id.pjgsisi2);
        pjgsisi3 = findViewById(R.id.pjgsisi3);
        keliling = findViewById(R.id.keliling);
    }


    public void onHitungLuas(View view) {
        try {
            als = Float.parseFloat(alas.getText().toString());
            tgg = Float.parseFloat(tinggi.getText().toString());

            ls = 0.5 * als * tgg;

            luas.setText("Luas = " + Float.toString((float) ls) + " cm^2");
        } catch (NumberFormatException e) {
            Log.d("Error", "Error Format" + e);
        }
    }

    public void onHitungKeliling(View view) {
        try {
            pjgss1 = Float.parseFloat(pjgsisi1.getText().toString());
            pjgss2 = Float.parseFloat(pjgsisi2.getText().toString());
            pjgss3 = Float.parseFloat(pjgsisi3.getText().toString());

            kl = pjgss1 + pjgss2 + pjgss3;

            keliling.setText("Keliling = " + Float.toString(kl) + " cm");
        } catch (NumberFormatException e) {
            Log.d("Error", "Error Format" + e);
        }
    }
}