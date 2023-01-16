package com.learning.modifutsmysql;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.learning.modifutsmysql.activity.CheckOutActivity;
import com.learning.modifutsmysql.activity.DetailActivity;
import com.learning.modifutsmysql.activity.InsertProductActivity;
import com.learning.modifutsmysql.activity.UpdateUserActivity;
import com.learning.modifutsmysql.adapter.ProductAdapter;
import com.learning.modifutsmysql.model.ProductModel;
import com.learning.modifutsmysql.util.AppController;
import com.learning.modifutsmysql.util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements ProductAdapter.ItemClickListener {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mManager;
    ArrayList<ProductModel> mItems;
    ProgressDialog pd;

    private ProductAdapter adapter;
    NumberFormat rpFormat = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));

    public TextView totalPrice;
    public double total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.recyclerView);
        pd = new ProgressDialog(MainActivity.this);
        mItems = new ArrayList<>();

        loadJson();

        adapter = new ProductAdapter(mItems);
        mManager = new GridLayoutManager(this, 2);

        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        totalPrice = findViewById(R.id.totalPrice);
        totalPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CheckOutActivity.class);
                intent.putExtra("total_price", total);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_call_center:
                Toast.makeText(getApplicationContext(), "Call Center",
                        Toast.LENGTH_SHORT).show();
                Intent iCall = new Intent(Intent.ACTION_DIAL);
                iCall.setData(Uri.parse("tel:087730393303"));
                if (iCall.resolveActivity(getPackageManager()) != null) {
                    startActivity(iCall);
                }
                return true;
            case R.id.action_sms_center:
                Toast.makeText(getApplicationContext(), "SMS Center",
                        Toast.LENGTH_SHORT).show();
                Intent iSMS = new Intent(Intent.ACTION_SENDTO);
                iSMS.setData(Uri.parse("smsto:087730393303"));
                if (iSMS.resolveActivity(getPackageManager()) != null) {
                    startActivity(iSMS);
                }
                return true;
            case R.id.action_lokasi:
                Toast.makeText(getApplicationContext(), "Lokasi",
                        Toast.LENGTH_SHORT).show();
                Intent iLoc = new Intent(Intent.ACTION_VIEW);
                iLoc.setData(Uri.parse("geo:0,0?q=Kantor+Kelurahan+Tanjung+Klego"));
                if (iLoc.resolveActivity(getPackageManager()) != null) {
                    startActivity(iLoc);
                }
                return true;
            case R.id.action_update_user:
                Toast.makeText(getApplicationContext(), "Update User",
                        Toast.LENGTH_SHORT).show();
                Intent iUpdUser = new Intent(this, UpdateUserActivity.class);
                startActivity(iUpdUser);
                return true;
                case R.id.action_insert_product:
                    Toast.makeText(getApplicationContext(), "Insert Product",
                            Toast.LENGTH_SHORT).show();
                Intent iInsProduct = new Intent(this, InsertProductActivity.class);
                startActivity(iInsProduct);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void postTotal() {
        totalPrice.setText("Total : " + rpFormat.format(total));
    }

    @Override
    public void onClick(View view, int position) {

        final ProductModel product = mItems.get(position);
        Double price = Double.parseDouble(product.getProduct_prce());

        switch (view.getId()) {
            case R.id.productName:
                Toast.makeText(this, product.getProduct_name(),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, DetailActivity.class);
                intent.putExtra("code", product.getProduct_code());
                intent.putExtra("name", product.getProduct_name());
                intent.putExtra("desc", product.getProduct_desc());
                intent.putExtra("prce", product.getProduct_prce());
                startActivity(intent);
                return;
            case R.id.productImg:
                total += price;
                Toast.makeText(this, rpFormat.format(price), Toast.LENGTH_SHORT).show();
                postTotal();
                return;
            default:
                break;
        }
    }

    private void loadJson() {

        pd.setMessage("Fetching Product Data...");
        pd.setCancelable(false);
        pd.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_DATA,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                pd.cancel();
                Log.d("Volley", "Response : " + response.toString());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject data = response.getJSONObject(i);
                        ProductModel mp = new ProductModel();
                        mp.setProduct_code(data.getString("product_code"));
                        mp.setProduct_name(data.getString("product_name"));
                        mp.setProduct_desc(data.getString("product_desc"));
                        mp.setProduct_prce(data.getString("product_prce"));
                        mItems.add(mp);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.cancel();
                Log.d("Volley", "Error : " + error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(reqData);
    }
}