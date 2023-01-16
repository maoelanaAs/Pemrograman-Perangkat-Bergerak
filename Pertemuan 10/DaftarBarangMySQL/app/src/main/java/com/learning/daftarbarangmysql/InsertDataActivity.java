package com.learning.daftarbarangmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.learning.daftarbarangmysql.util.AppController;
import com.learning.daftarbarangmysql.util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertDataActivity extends AppCompatActivity {

    EditText kodeBrgET, namaBrgET, hrgBeliET, hrgJualET, stokBrgET;
    Button btnbatal, btnsimpan;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        Intent data = getIntent();
        final int update = data.getIntExtra("update", 0);
        String intent_kode_brg = data.getStringExtra("kode_brg");
        String intent_nama_brg = data.getStringExtra("nama_brg");
        String intent_hrg_beli = data.getStringExtra("hrg_beli");
        String intent_hrg_jual = data.getStringExtra("hrg_jual");
        String intent_stok_brg = data.getStringExtra("stok_brg");

        kodeBrgET = findViewById(R.id.kodeBrgET);
        namaBrgET = findViewById(R.id.namaBrgET);
        hrgBeliET = findViewById(R.id.hrgBeliET);
        hrgJualET = findViewById(R.id.hrgJualET);
        stokBrgET = findViewById(R.id.stokBrgET);
        btnbatal = findViewById(R.id.btn_cancel);
        btnsimpan = findViewById(R.id.btn_simpan);

        pd = new ProgressDialog(InsertDataActivity.this);

        if (update == 1) {
            btnsimpan.setText("Update Data");
            kodeBrgET.setText(intent_kode_brg);
            kodeBrgET.setVisibility(View.GONE);
            namaBrgET.setText(intent_nama_brg);
            hrgBeliET.setText(intent_hrg_beli);
            hrgJualET.setText(intent_hrg_jual);
            stokBrgET.setText(intent_stok_brg);
        }

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (update == 1) {
                    updateData();
                } else {
                    simpanData();
                }
            }
        });

        btnbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new
                        Intent(InsertDataActivity.this, MainActivity.class);
                startActivity(main);
            }
        });
    }

    private void simpanData() {
        pd.setMessage("Menyimpan Data...");
        pd.setCancelable(false);
        pd.show();
        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_INSERT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.cancel();

                try {
                    JSONObject res = new JSONObject(response);
                    Toast.makeText(InsertDataActivity.this, "Message: " + res.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(InsertDataActivity.this, MainActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void
            onErrorResponse(VolleyError error) {
                pd.cancel();
                Toast.makeText(InsertDataActivity.this, "Message: Failed! Data Gagal ditambahkan", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("kode_brg", kodeBrgET.getText().toString());
                map.put("nama_brg", namaBrgET.getText().toString());
                map.put("hrg_beli", hrgBeliET.getText().toString());
                map.put("hrg_jual", hrgJualET.getText().toString());
                map.put("stok_brg", stokBrgET.getText().toString());
                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(sendData);
    }

    private void updateData() {
        pd.setMessage("Mengubah Data...");
        pd.setCancelable(false);
        pd.show();
        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.cancel();

                try {
                    JSONObject res = new JSONObject(response);
                    Toast.makeText(InsertDataActivity.this, "Message: " + res.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(InsertDataActivity.this, MainActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.cancel();
                Toast.makeText(InsertDataActivity.this, "Message: Failed! Data gagal diubah", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("kode_brg", kodeBrgET.getText().toString());
                map.put("nama_brg", namaBrgET.getText().toString());
                map.put("hrg_beli", hrgBeliET.getText().toString());
                map.put("hrg_jual", hrgJualET.getText().toString());
                map.put("stok_brg", stokBrgET.getText().toString());
                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(updateReq);
    }
}