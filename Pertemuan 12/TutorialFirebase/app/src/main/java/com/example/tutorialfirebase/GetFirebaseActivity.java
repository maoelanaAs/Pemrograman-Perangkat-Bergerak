package com.example.tutorialfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetFirebaseActivity extends Activity implements AdapterView.OnItemClickListener {

    Activity activity;
    ListView listView;
    ListAdapter adapter;
    private ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    Button btnBack;
    DatabaseReference rootRef, demoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_firebase);

        activity = this;
        btnBack = (Button) findViewById(R.id.btnBack);
        listView = findViewById(R.id.listview);
        listView.setOnItemClickListener(this);

        listView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        rootRef = FirebaseDatabase.getInstance().getReference();
        demoRef = rootRef.child("barang");
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getDataValue();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View
            view, int position, long id) {
    }

    public void getDataValue() {
        list = new ArrayList<HashMap<String, String>>();
        rootRef.child("barang")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int i = 1;
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String id = (String) snapshot.getKey();
                            String nama = (String) snapshot.child("nama").getValue();
                            String merk = (String) snapshot.child("merk").getValue();
                            String harga = (String) snapshot.child("harga").getValue();
                            HashMap<String, String> data = new HashMap<>();
                            data.put("key", id);
                            data.put("nomor", "" + i++);
                            data.put("nama", nama);
                            data.put("merk", merk);
                            data.put("harga", harga);
                            list.add(data);
                        }
                        adapter = new ListAdapter(activity, list, R.layout.list_item,
                                new String[]{"nomor", "nama", "merk", "harga"},
                                new int[]{R.id.nomor, R.id.textNama, R.id.textMerk, R.id.harga});
                        Parcelable state =
                                listView.onSaveInstanceState();
                        listView.setAdapter(adapter);
                        listView.onRestoreInstanceState(state);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

    public class ListAdapter extends SimpleAdapter {
        private Context mContext;
        public LayoutInflater inflater = null;

        public ListAdapter(Context context, List<? extends Map<String, ?>> data,
                           int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
            mContext = context;
            inflater = (LayoutInflater)
                    mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE
                    );
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View vi = convertView;
            if (convertView == null)
                vi = inflater.inflate(R.layout.list_item, null);
            HashMap<String, Object> data = (HashMap<String, Object>) getItem(position);
            final Button btnDelete = vi.findViewById(R.id.delete);
            final TextView nomor = vi.findViewById(R.id.nomor);
            final TextView textNama = vi.findViewById(R.id.textNama);
            final TextView textMerk = vi.findViewById(R.id.textMerk);
            final TextView harga = vi.findViewById(R.id.harga);
            final String strKey = (String) data.get("key");
            final String strID = (String) data.get("nomor");
            final String strTextNama = (String) data.get("nama");
            final String strTextMerk = (String) data.get("merk");
            final String strHarga = (String) data.get("harga");
            nomor.setText(strID);
            textNama.setText(strTextNama);
            textMerk.setText(strTextMerk);
            harga.setText(strHarga);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Delete(strKey);
                }
            });
            return vi;
        }
    }

    public void Delete(String keyID) {
        Query applesQuery = demoRef.child(keyID);
        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot
                                             dataSnapshot) {
                for (DataSnapshot dummySnapshot :
                        dataSnapshot.getChildren()) {
                    dummySnapshot.getRef().removeValue();
                }
                getDataValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("delete", "onCancelled",
                        databaseError.toException());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}