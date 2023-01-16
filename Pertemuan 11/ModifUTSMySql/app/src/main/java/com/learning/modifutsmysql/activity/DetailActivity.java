package com.learning.modifutsmysql.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    TextView detailName, detailDesc, detailPrice;
    Button updateBtn, deleteBtn;

    String productCode, productName, productDesc, productPrice;
    Double price;

    ProgressDialog pd;

    NumberFormat rpFormat = NumberFormat.getCurrencyInstance
            (new Locale("in", "ID"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        pd = new ProgressDialog(DetailActivity.this);

        detailName = findViewById(R.id.detailName);
        detailDesc = findViewById(R.id.detailDesc);
        detailPrice = findViewById(R.id.detailPrice);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        productCode = getIntent().getStringExtra("code");
        productName = getIntent().getStringExtra("name");
        productDesc = getIntent().getStringExtra("desc");
        productPrice = getIntent().getStringExtra("prce");
        price = Double.parseDouble(productPrice);

        detailName.setText(productName);
        detailDesc.setText(productDesc);
        detailPrice.setText(rpFormat.format(price));

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, InsertProductActivity.class);

                intent.putExtra("update", 1);
                intent.putExtra("product_code", productCode);
                intent.putExtra("product_name", productName);
                intent.putExtra("product_desc", productDesc);
                intent.putExtra("product_price", productPrice);

                startActivity(intent);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Deleting Product Data...");
                pd.setCancelable(false);
                pd.show();
                StringRequest deleteReq = new StringRequest(Request.Method.POST, ServerAPI.URL_DELETE,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                pd.cancel();
                                Log.d("Volley", "Response : " + response.toString());
                                try {
                                    JSONObject res = new JSONObject(response);
                                    Toast.makeText(DetailActivity.this, "Message : " + res.getString("message"), Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                startActivity(new Intent(DetailActivity.this, MainActivity.class));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "Error: " + error.getMessage());
                        Toast.makeText(DetailActivity.this, "Message: Data gagal dihapus!!", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productCode);

                        return map;
                    }
                };
                AppController.getInstance().addToRequestQueue(deleteReq);
            }
        });
    }
}


