package com.learning.kalkulatorbidangdatar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText input1, input2;
    TextView luas, keliling, note;
    Double inp1, inp2, ls, kl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        luas = findViewById(R.id.luas);
        keliling = findViewById(R.id.keliling);
        note = findViewById(R.id.note);
    }

    public void onPersegi(View view) {
        inp1 = Double.parseDouble(input1.getText().toString());
        inp2 = Double.parseDouble(input2.getText().toString());

        ls = inp1 * inp2;
        kl = 2 * (inp1 + inp2);

        luas.setText("Luas Persegi\n" + Double.toString(ls));
        keliling.setText("Keliling Persegi\n" + Double.toString(kl));
        note.setText("");
    }

    public void onSegitiga(View view) {
        inp1 = Double.parseDouble(input1.getText().toString());
        inp2 = Double.parseDouble(input2.getText().toString());

        ls = 0.5 * inp1 * inp2;
        kl = inp1 * 3;

        luas.setText("Luas Segitiga\n" + Double.toString(ls));
        keliling.setText("Keliling Segitiga\n" + Double.toString(kl));
        note.setText("*segitiga sama sisi");
    }

    public void onLingkaran(View view) {
        inp1 = Double.parseDouble(input1.getText().toString());

        Double jari = inp1 / 2;

        ls = 3.14 * jari * jari;
        kl = 2 * 3.14 * inp1;

        luas.setText("Luas Lingkaran\n" + Double.toString(ls));
        keliling.setText("Keliling Lingkaran\n" + Double.toString(kl));
        note.setText("");
    }
}