package com.android.vik.inventoryapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Products.db";

    static final String TEXT_TYPE = " TEXT ";
    static final String INTEGER_TYPE = " INTEGER ";
    static final String COMMA_SEP = " , ";
    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ProductContract.ProductEntry.TABLE_NAME +
                    " ( " +
                    ProductContract.ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT " + COMMA_SEP +
                    ProductContract.ProductEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    ProductContract.ProductEntry.COLUMN_NAME_QUANTITY + INTEGER_TYPE + COMMA_SEP +
                    ProductContract.ProductEntry.COLUMN_NAME_PRICE_DOLLAR + INTEGER_TYPE + COMMA_SEP +
                    ProductContract.ProductEntry.COLUMN_NAME_PRICE_CENTS + INTEGER_TYPE + COMMA_SEP +
                    ProductContract.ProductEntry.COLUMN_NAME_SUPPLIER_PHONE_NUMBER + TEXT_TYPE + COMMA_SEP +
                    ProductContract.ProductEntry.COLUMN_NAME_IMAGE_RES_ID + INTEGER_TYPE +
                    " ) ";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ProductContract.ProductEntry.TABLE_NAME;

    public ProductDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static String getSqlDeleteEntries() {
        return SQL_DELETE_ENTRIES;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(getSqlDeleteEntries());
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
