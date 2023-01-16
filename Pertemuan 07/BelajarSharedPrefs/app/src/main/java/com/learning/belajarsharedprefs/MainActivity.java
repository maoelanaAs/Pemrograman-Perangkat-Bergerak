package com.learning.belajarsharedprefs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtNama, txtAlamat;

    private SharedPreferences mPreferences;
    private static final String mSharedPrefFile
            = "com.learning.belajarsharedprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNama = (EditText) findViewById(R.id.in_nama);
        txtAlamat = (EditText) findViewById(R.id.in_alamat);
    }

    public void save(View view){

        mPreferences = getSharedPreferences(mSharedPrefFile, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();

        editor.putString("namaku", txtNama.getText().toString());
        editor.putString("alamatku", txtAlamat.getText().toString());
        editor.commit();
        pesan();
    }

    public void cancel(View view){

        txtNama.setText("");
        txtAlamat.setText("");
    }

    private void pesan(){

        AlertDialog.Builder psn = new AlertDialog.Builder(this);
        psn.setTitle("Konfirmasi");
        psn.setMessage("Data Tersimpan");
        psn.setIcon(R.drawable.ic_alert_dialog);
        psn.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(getApplicationContext(), "Berhasil Menyimpan",
                        Toast.LENGTH_SHORT).show();
            }
        });
        psn.show();
    }
}