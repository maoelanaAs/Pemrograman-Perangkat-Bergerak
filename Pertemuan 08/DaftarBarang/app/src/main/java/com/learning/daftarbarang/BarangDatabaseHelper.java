package com.learning.daftarbarang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BarangDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "db_usaha";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_BARANG = "tb_barang";
    private static final String KEY_KODEBRG = "kode_brg";
    private static final String KEY_NAMABRG = "nama_brg";
    private static final String KEY_HRGBELI = "hrg_beli";
    private static final String KEY_HRGJUAL = "hrg_jual";
    private static final String KEY_STOKBRG = "stok_brg";

    BarangDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_query = "CREATE TABLE " + TABLE_BARANG + " (" +
                KEY_KODEBRG + " TEXT PRIMARY KEY, " +
                KEY_NAMABRG + " TEXT, " +
                KEY_HRGBELI + " INTEGER, " +
                KEY_HRGJUAL + " INTEGER, " +
                KEY_STOKBRG + " INTEGER);";

        db.execSQL(create_table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BARANG);
        onCreate(db);
    }

    void insBarang(String kodeBrg, String namaBrg, int hrgBeli, int hrgJual, int stokBrg) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_KODEBRG, kodeBrg);
        cv.put(KEY_NAMABRG, namaBrg);
        cv.put(KEY_HRGBELI, hrgBeli);
        cv.put(KEY_HRGJUAL, hrgJual);
        cv.put(KEY_STOKBRG, stokBrg);

        long result = db.insert(TABLE_BARANG, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Gagal Menambah Data Barang!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil Menambah Data Barang!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor getAllBarang() {
        String select_table_query = "SELECT * FROM " + TABLE_BARANG;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(select_table_query, null);
        }

        return cursor;
    }

    void updBarang(String kodeBrg, String namaBrg, int hrgBeli, int hrgJual, int stokBrg) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_KODEBRG, kodeBrg);
        cv.put(KEY_NAMABRG, namaBrg);
        cv.put(KEY_HRGBELI, hrgBeli);
        cv.put(KEY_HRGJUAL, hrgJual);
        cv.put(KEY_STOKBRG, stokBrg);

        long result = db.update(TABLE_BARANG, cv, KEY_KODEBRG + "=?", new String[]{kodeBrg});
        if (result == -1) {
            Toast.makeText(context, "Gagal Mengubah Data Barang!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil Mengubah Data Barang!", Toast.LENGTH_SHORT).show();
        }
    }

    void delBarang(String kodeBrg) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_BARANG, KEY_KODEBRG + "=?", new String[]{kodeBrg});
        if (result == -1) {
            Toast.makeText(context, "Gagal Menghapus Data Barang!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil Menghapus Data Barang!", Toast.LENGTH_SHORT).show();
        }
    }
}
