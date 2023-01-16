package com.learning.modifutssqlite.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

public class ProductDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "penjualan.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PRODUCT = "product";
    private static final String KEY_PRODUCTCODE = "product_code";
    private static final String KEY_PRODUCTNAME = "product_name";
    private static final String KEY_PRODUCTDESC = "product_desc";
    private static final String KEY_PRODUCTPRICE = "product_price";
    private static final String KEY_PRODUCTIMAGE = "product_image";

    ProductDatabaseHelper(@NonNull Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_query = "CREATE TABLE " + TABLE_PRODUCT + " (" +
                KEY_PRODUCTCODE + " INT PRIMARY KEY, " +
                KEY_PRODUCTNAME + " TEXT, " +
                KEY_PRODUCTDESC + " TEXT, " +
                KEY_PRODUCTPRICE + " INT, " +
                KEY_PRODUCTIMAGE + " TEXT);";
        db.execSQL(create_table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
