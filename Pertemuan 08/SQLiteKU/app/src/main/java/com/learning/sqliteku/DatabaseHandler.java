package com.learning.sqliteku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Sekolah";
    private static final String TABLE_SISWA = "Siswa";
    private static final String KEY_NIS = "nis";
    private static final String KEY_NAMA = "nama";

    DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_create_siswa = "CREATE TABLE " + TABLE_SISWA + "(" + KEY_NIS +
                " TEXT PRIMARY KEY, " + KEY_NAMA + " TEXT)";
        db.execSQL(query_create_siswa);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SISWA);
        onCreate(db);
    }

    public void addSiswa(Siswa siswa) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NIS, siswa.getNis());
        values.put(KEY_NAMA, siswa.getNama());

        db.insert(TABLE_SISWA, null, values);
        db.close();
    }

    public Siswa getSiswa(String nis) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SISWA, new String[]{KEY_NIS, KEY_NAMA}, KEY_NIS + "=?",
                new String[]{nis}, null, null, null, null);

        if (cursor != null) cursor.moveToFirst();

        Siswa siswa = new Siswa(cursor.getString(0), cursor.getString(1));

        return siswa;
    }

    public List<Siswa> getSemuaSiswa() {
        List<Siswa> siswaList = new ArrayList<Siswa>();
        String query_select_siswa = "SELECT * FROM " + TABLE_SISWA;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query_select_siswa, null);

        if (cursor.moveToFirst()) {
            do {
                Siswa siswa = new Siswa(cursor.getString(0), cursor.getString(1));
                siswaList.add(siswa);
            } while (cursor.moveToNext());
        }

        return siswaList;
    }

    public void deleteSiswa(Siswa siswa) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_SISWA, KEY_NIS + "='" + siswa.getNis() + "'", null);
        db.close();

        System.out.println("Data terhapus " + siswa.getNis());
    }

    public void deleteRow(String xnis) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_SISWA, KEY_NIS + "='" + xnis + "'", null);
        db.close();
        System.out.println("Data terhapus " + xnis);
    }

    public void updateMethod(String nis, String nama) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE " + TABLE_SISWA + " SET nama = '" + nama + "' WHERE nis ='" + nis + "'");
        db.close();
        System.out.println("Data sudah di update " + nis);
    }
}
