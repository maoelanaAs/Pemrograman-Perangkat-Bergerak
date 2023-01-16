package com.learning.navdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class FormActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText inNama, inAlamat, inDate, inEmail;
    Spinner spinner;
    Calendar calendar;
    String spinnerItem, jenisKelamin;
    ArrayList<String> hobi = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        inNama = findViewById(R.id.inNama);
        inAlamat = findViewById(R.id.inAlamat);
        spinner = findViewById(R.id.daftarKota);
        inDate = findViewById(R.id.inDate);
        calendar = Calendar.getInstance();
        inEmail = findViewById(R.id.inEmail);

        // Spinner Kota
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.daftar_kota, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (spinner != null) {
            spinner.setAdapter(adapter);
        }

        // Datepicker Tgl Lahir
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                inDate.setText(dateFormat.format(calendar.getTime()));
            }
        };

        inDate.setOnClickListener(view -> {
            new DatePickerDialog(FormActivity.this, date,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        });
    }

    public void onItemSelected(AdapterView<?> adapterView, View view,
                               int pos, long id) {
        spinnerItem = adapterView.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.lakilaki:
                if (checked) {
                    jenisKelamin = "Laki-laki";
                }
                break;
            case R.id.perempuan:
                if (checked) {
                    jenisKelamin = "Perempuan";
                }
                break;
            default:
                    jenisKelamin = "";
                break;
        }
    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){
            case R.id.membaca:
                if (checked){
                    hobi.add("Membaca");
                } else {
                    hobi.remove("Membaca");
                }
                break;
            case R.id.menulis:
                if (checked){
                    hobi.add("Menulis");
                } else {
                    hobi.remove("Menulis");
                }
                break;
            case R.id.bersepeda:
                if (checked){
                    hobi.add("Bersepeda");
                } else {
                    hobi.remove("Bersepeda");
                }
                break;
            default:
                hobi.clear();
                break;
        }
    }


    public void btnOk(View view) {

        AlertDialog.Builder alrt = new AlertDialog.Builder(this);

        alrt.setTitle("Konfirmasi");
        alrt.setMessage("Klik OK untuk Simpan!")
                .setIcon(R.drawable.ic_alert)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Toast.makeText(getApplicationContext(), "Data Tersimpan",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alrt.create();

        alertDialog.show();
    }

    public void btnCancel(View view) {
 
        inNama.setText("");
        inAlamat.setText("");
        inDate.setText("");
        inEmail.setText("");
    }
}