package com.learning.modifutsmysql.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.learning.modifutsmysql.MainActivity;
import com.learning.modifutsmysql.R;
import com.learning.modifutsmysql.util.AppController;
import com.learning.modifutsmysql.util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertProductActivity extends AppCompatActivity {

    TextView titleTV;
    EditText productNameET, productPriceET, productDescET;
    Button saveBtn, cancelBtn;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);

        Intent product = getIntent();
        final int update = product.getIntExtra("update", 0);
        String intent_product_code = product.getStringExtra("product_code");
        String intent_product_name = product.getStringExtra("product_name");
        String intent_product_desc = product.getStringExtra("product_desc");
        String intent_product_prce = product.getStringExtra("product_price");

        titleTV = findViewById(R.id.titleTV);
        productNameET = findViewById(R.id.productNameET);
        productPriceET = findViewById(R.id.productPriceET);
        productDescET = findViewById(R.id.productDescET);

        saveBtn = findViewById(R.id.saveBtn);
        cancelBtn = findViewById(R.id.cancelBtn);

        pd = new ProgressDialog(InsertProductActivity.this);

        if (update == 1) {
            titleTV.setText("Update Product Data");
            productNameET.setText(intent_product_name);
            productPriceET.setText(intent_product_prce);
            productDescET.setText(intent_product_desc);
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (update == 1) {
                    updateData(intent_product_code);
                } else {
                    insertData();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(InsertProductActivity.this, MainActivity.class);
                startActivity(main);
            }
        });
    }

    private void insertData() {
        pd.setMessage("Inserting Product Data...");
        pd.setCancelable(false);
        pd.show();
        StringRequest insertReq = new StringRequest(Request.Method.POST,
                ServerAPI.URL_INSERT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.cancel();
                try {
                    JSONObject res = new JSONObject(response);
                    Toast.makeText(InsertProductActivity.this,
                            "Message : " + res.getString("message"),
                            Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(InsertProductActivity.this, MainActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.cancel();
                Toast.makeText(InsertProductActivity.this, "Message : Product failed to insert",
                        Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("product_name", productNameET.getText().toString());
                map.put("product_prce", productPriceET.getText().toString());
                map.put("product_desc", productDescET.getText().toString());
                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(insertReq);
    }

    private void updateData(String intent_product_code) {
        pd.setMessage("Updating Product Data...");
        pd.setCancelable(false);
        pd.show();
        StringRequest updateReq = new StringRequest(Request.Method.POST,
                ServerAPI.URL_UPDATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.cancel();

                try {
                    JSONObject res = new JSONObject(response);
                    Toast.makeText(InsertProductActivity.this,
                            "Message : " + res.getString("message"),
                            Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(InsertProductActivity.this, MainActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.cancel();
                Toast.makeText(InsertProductActivity.this,
                        "Message: Product failed to update", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("product_code", intent_product_code);
                map.put("product_name", productNameET.getText().toString());
                map.put("product_prce", productPriceET.getText().toString());
                map.put("product_desc", productDescET.getText().toString());
                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(updateReq);
    }
}