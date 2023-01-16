package com.learning.luaskeliling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void runPersegi(View view) {
        Intent i = new Intent(view.getContext(), PersegiActivity.class);
        view.getContext().startActivity(i);
    }

    public void runSegitiga(View view) {
        Intent i = new Intent(view.getContext(), SegitigaActivity.class);
        view.getContext().startActivity(i);
    }

    public void runLingkaran(View view) {
        Intent i = new Intent(view.getContext(), LingkaranActivity.class);
        view.getContext().startActivity(i);
    }

    public void runKubus(View view) {
        Intent i = new Intent(view.getContext(), KubusActivity.class);
        view.getContext().startActivity(i);
    }
}