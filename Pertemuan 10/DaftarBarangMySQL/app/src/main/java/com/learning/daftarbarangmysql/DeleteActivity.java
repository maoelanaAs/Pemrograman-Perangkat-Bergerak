package com.learning.daftarbarangmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class DeleteActivity extends AppCompatActivity {

    EditText kodeBrgPARAM;
    Button btnDelete;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        kodeBrgPARAM = findViewById(R.id.kodeBrgPARAM);
        btnDelete = findViewById(R.id.btn_delete);
        pd = new ProgressDialog(DeleteActivity.this);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });
    }

    private void deleteData() {
        pd.setMessage("Menghapus Data...");
        pd.setCancelable(false);
        pd.show();
        StringRequest delReq = new StringRequest(Request.Method.POST, ServerAPI.URL_DELETE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.cancel();
                Log.d("volley", "Response: " + response.toString());
                try {
                    JSONObject res = new JSONObject(response);
                    Toast.makeText(DeleteActivity.this, "Message: " + res.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(DeleteActivity.this, MainActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void
            onErrorResponse(VolleyError error) {
                pd.cancel();
                Log.d("volley", "Error: " + error.getMessage());
                Toast.makeText(DeleteActivity.this, "Message: Failed! Data gagal dihapus", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("kode_brg", kodeBrgPARAM.getText().toString());
                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(delReq);
    }
}