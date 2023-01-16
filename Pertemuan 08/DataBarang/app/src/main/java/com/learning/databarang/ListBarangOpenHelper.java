package com.learning.databarang;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ListBarangOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = ListBarangOpenHelper.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_usaha";
    private static final String TABLE_BARANG = "tb_barang";

    private static final String KEY_KODEBRG = "kode_brg";
    private static final String KEY_NAMABRG = "nama_brg";
    private static final String KEY_HRGBELI = "hrg_beli";
    private static final String KEY_HRGJUAL = "hrg_jual";
    private static final String KEY_STOKBRG = "stok_brg";

    private static final String TABLE_BARANG_CREATE =
            "CREATE TABLE " + TABLE_BARANG + " (" +
                    KEY_KODEBRG + " TEXT PRIMARY KEY, " +
                    KEY_NAMABRG + " TEXT, " +
                    KEY_HRGBELI + " INTEGER, " +
                    KEY_HRGJUAL + " INTEGER, " +
                    KEY_STOKBRG + " INTEGER);";

    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;

    ListBarangOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "Construct ListBarangOpenHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_BARANG_CREATE);
        fillDatabaseWithData(db);
    }

    private void fillDatabaseWithData(SQLiteDatabase db) {
        String[] kodeBrgS = {"B0001", "B0002"};
        String[] namaBrgS = {"Buku", "Spidol"};
        int[] hrgBeliS = {5000, 3500};
        int[] hrgJualS = {6000, 4000};
        int[] stokBrgS = {100, 50};

        ContentValues values = new ContentValues();

        for (int i = 0; i < kodeBrgS.length; i++) {
            values.put(KEY_KODEBRG, kodeBrgS[i]);
            values.put(KEY_NAMABRG, namaBrgS[i]);
            values.put(KEY_HRGBELI, hrgBeliS[i]);
            values.put(KEY_HRGJUAL, hrgJualS[i]);
            values.put(KEY_STOKBRG, stokBrgS[i]);
            db.insert(TABLE_BARANG, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ListBarangOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BARANG);
        onCreate(db);
    }
}
