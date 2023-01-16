package com.learning.luaskeliling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LingkaranActivity extends AppCompatActivity {

    EditText jari2;
    TextView luas, keliling;
    Float jr2;
    double ls;
    double kl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lingkaran);

        jari2 = findViewById(R.id.jari2);
        luas = findViewById(R.id.luas);
        keliling = findViewById(R.id.keliling);
    }

    public void onHitung(View view) {
        try {
            jr2 = Float.parseFloat(jari2.getText().toString());

            ls = Math.PI * jr2 * jr2;
            kl = Math.PI * 2 * jr2;

            luas.setText("Luas = " + Float.toString((float) ls) + " cm^2");
            keliling.setText("Keliling = " + Float.toString((float) kl) + " cm");
        } catch (NumberFormatException e) {
            Log.d("Error", "Error Format" + e);
        }
    }
}