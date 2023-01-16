package com.learning.luaskeliling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class KubusActivity extends AppCompatActivity {

    EditText sisi;
    TextView luas, keliling;
    Float ss, ls, kl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kubus);

        sisi = findViewById(R.id.sisi);
        luas = findViewById(R.id.luas);
        keliling = findViewById(R.id.keliling);
    }

    public void onHitung(View view) {
        try {
            ss = Float.parseFloat(sisi.getText().toString());

            ls = 6 * ss * ss;
            kl = 12 * ss;

            luas.setText("Luas = " + Float.toString(ls) + " cm^2");
            keliling.setText("Keliling = " + Float.toString(kl) + " cm");
        } catch (NumberFormatException e) {
            Log.d("Error", "Error Format" + e);
        }
    }
}