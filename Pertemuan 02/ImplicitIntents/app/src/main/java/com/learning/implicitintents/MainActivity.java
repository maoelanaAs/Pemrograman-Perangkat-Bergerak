package com.learning.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mWebEditText;
    private EditText mLocEditText;
    private EditText mShareEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebEditText = (EditText) findViewById(R.id.web_editText);
        mLocEditText = (EditText) findViewById(R.id.loc_editText);
        mShareEditText = (EditText) findViewById(R.id.share_editText);
    }

    public void openWeb(View view) {
        String url = mWebEditText.getText().toString();
        Uri webPage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void openLoc(View view) {
        String loc = mLocEditText.getText().toString();
        Uri addressUrl = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUrl);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void shareText(View view) {
        String txt = mShareEditText.getText().toString();
        String mimetype = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimetype)
                .setChooserTitle("Bagikan teks ini dengan : ")
                .setText(txt)
                .startChooser();
    }
}