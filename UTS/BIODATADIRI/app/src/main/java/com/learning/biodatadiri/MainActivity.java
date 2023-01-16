package com.learning.biodatadiri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAlamat(View view) {
        Uri geoLocation = Uri.parse("geo:0,0?q=Kost+Putra+Alfahanin+05");
        Intent intent = new Intent(Intent.ACTION_VIEW, geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("Error : ", "Can't handle this intent!");
        }
    }

    public void onTelepon(View view) {
        Uri phoneNumber = Uri.parse("tel:087730393303");
        Intent intent = new Intent(Intent.ACTION_DIAL, phoneNumber);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("Error : ", "Can't handle this intent!");
        }
    }

    public void onEmail(View view) {
        Uri emailAddress = Uri.parse("mailto:maulanaasan10@gmail.com");
        Intent intent = new Intent(Intent.ACTION_SENDTO, emailAddress);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("Error : ", "Can't handle this intent!");
        }
    }
}