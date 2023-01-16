package com.learning.simplecalc;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText angka1, angka2;
    private TextView hasil;
    private Float bil1, bil2;
    private double hsl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        angka1 = findViewById(R.id.angka1);
        angka2 = findViewById(R.id.angka2);
        hasil = findViewById(R.id.hasil);
    }


    public void onAdd(View view) {
        try {
            bil1 = Float.parseFloat(angka1.getText().toString());
            bil2 = Float.parseFloat(angka2.getText().toString());

            hsl = bil1 + bil2;

            hasil.setText(Float.toString(bil1) + " + " + Float.toString(bil2) +
                    " = " + Float.toString((float) hsl));
        } catch (NumberFormatException e){
            Log.d("Error", "Error Format..."+e);
        }
    }

    public void onSub(View view) {
        try {
            bil1 = Float.parseFloat(angka1.getText().toString());
            bil2 = Float.parseFloat(angka2.getText().toString());

            hsl = bil1 - bil2;

            hasil.setText(Float.toString(bil1) + " - " + Float.toString(bil2) +
                    " = " + Float.toString((float) hsl));
        } catch (NumberFormatException e){
            Log.d("Error", "Error Format..."+e);
        }
    }

    public void onMul(View view) {
        try {
            bil1 = Float.parseFloat(angka1.getText().toString());
            bil2 = Float.parseFloat(angka2.getText().toString());

            hsl = bil1 * bil2;

            hasil.setText(Float.toString(bil1) + " * " + Float.toString(bil2) +
                    " = " + Float.toString((float) hsl));
        } catch (NumberFormatException e){
            Log.d("Error", "Error Format..."+e);
        }
    }

    public void onDiv(View view) {
        try {
            bil1 = Float.parseFloat(angka1.getText().toString());
            bil2 = Float.parseFloat(angka2.getText().toString());

            hsl = bil1 / bil2;

            hasil.setText(Float.toString(bil1) + " / " + Float.toString(bil2) +
                    " = " + Float.toString((float) hsl));
        } catch (NumberFormatException e){
            Log.d("Error", "Error Format..."+e);
        }
    }

    public void onSqrd(View view) {
        try {
            bil1 = Float.parseFloat(angka1.getText().toString());
            bil2 = Float.parseFloat(angka2.getText().toString());

            hsl = pow(bil1, 2);

            hasil.setText(Float.toString(bil1) + " ** 2 = " + Float.toString((float) hsl));
        } catch (NumberFormatException e){
            Log.d("Error", "Error Format..."+e);
        }
    }

    public void onSqrt(View view) {
        try {
            bil1 = Float.parseFloat(angka1.getText().toString());
            bil2 = Float.parseFloat(angka2.getText().toString());

            hsl = sqrt(bil1);

            hasil.setText(Float.toString(bil1) + " sqrt = " + Float.toString((float) hsl));
        } catch (NumberFormatException e){
            Log.d("Error", "Error Format..."+e);
        }
    }

    public void onMax(View view) {
        try {
            bil1 = Float.parseFloat(angka1.getText().toString());
            bil2 = Float.parseFloat(angka2.getText().toString());

            if (bil1 > bil2) {
                hsl = bil1;
            } else {
                hsl = bil2;
            }
            hasil.setText("Max = " + Float.toString((float) hsl));
        } catch (NumberFormatException e){
            Log.d("Error", "Error Format..."+e);
        }
    }

    public void onMin(View view) {
        try {
            bil1 = Float.parseFloat(angka1.getText().toString());
            bil2 = Float.parseFloat(angka2.getText().toString());

            if (bil1 < bil2) {
                hsl = bil1;
            } else {
                hsl = bil2;
            }
            hasil.setText("Min = " + Float.toString((float) hsl));
        } catch (NumberFormatException e){
            Log.d("Error", "Error Format..."+e);
        }
    }

    public void onRst(View view) {
        try {
            hasil.setText("=");
        } catch (NumberFormatException e){
            Log.d("Error", "Error Format..."+e);
        }
    }
}