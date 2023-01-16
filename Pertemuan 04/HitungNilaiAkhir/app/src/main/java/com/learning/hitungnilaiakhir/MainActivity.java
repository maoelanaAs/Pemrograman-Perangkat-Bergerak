package com.learning.hitungnilaiakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText ETnTgs, ETnUTS, ETnUAS;
    TextView TVnTgs, TVnUTS, TVnUAS;
    TextView TVnAkhir, TVnHuruf, TVPredikat;
    float nTgs, PnTgs = 0, nUTS, PnUTS = 0, nUAS, PnUAS = 0, nAkhir;
    String nHuruf, predikat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ETnTgs = findViewById(R.id.nTgstextE);
        TVnTgs = findViewById(R.id.nTgs);

        ETnUTS = findViewById(R.id.nUTStextE);
        TVnUTS = findViewById(R.id.nUTS);

        ETnUAS = findViewById(R.id.nUAStextE);
        TVnUAS = findViewById(R.id.nUAS);

        TVnAkhir = findViewById(R.id.nAkhir);
        TVnHuruf = findViewById(R.id.nHuruf);
        TVPredikat = findViewById(R.id.predikat);

        ETnTgs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (ETnTgs.getText().toString().isEmpty()) {
                    nTgs = 0;
                } else {
                    nTgs = Float.parseFloat(ETnTgs.getText().toString());
                }
                PnTgs = 0.3f * nTgs;
                TVnTgs.setText(Float.toString(PnTgs));
            }
        });

        ETnUTS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (ETnUTS.getText().toString().isEmpty()) {
                    nUTS = 0;
                } else {
                    nUTS = Float.parseFloat(ETnUTS.getText().toString());
                }
                PnUTS = 0.35f * nUTS;
                TVnUTS.setText(Float.toString(PnUTS));
            }
        });

        ETnUAS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (ETnUAS.getText().toString().isEmpty()) {
                    nUAS = 0;
                } else {
                    nUAS = Float.parseFloat(ETnUAS.getText().toString());
                }
                PnUAS = 0.35f * nUAS;
                TVnUAS.setText(Float.toString(PnUAS));
            }
        });
    }


    public void onHitung(View view) {
        Nilai nilai = new Nilai();

        nAkhir = nilai.getNAkhir(PnTgs, PnUTS, PnUAS);
        nHuruf = nilai.getNHuruf(nAkhir);
        predikat = nilai.getPredikat(nHuruf);

        TVnAkhir.setText("= " + Float.toString(nAkhir));
        TVnHuruf.setText("= " + nHuruf);
        TVPredikat.setText("= " + predikat);
    }

}