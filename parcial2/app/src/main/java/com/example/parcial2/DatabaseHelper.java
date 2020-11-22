package com.example.parcial2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME_PRODUCTS = "PRODUCT";
    public static final String TABLE_NAME_CATEGORIES = "CATEGORY";

    //Producto
    public static final String PRODUCT_ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String CATEGORY_ID = "id";


    private static final String DB_NAME = "PARCIAL2.DB";
    private static final int DB_VERSION = 1;

    private static final String TABLE_CATEGORY = "CREATE TABLE " + TABLE_NAME_CATEGORIES + "( " + CATEGORY_ID + " INTENGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " VARCHAR NOT NULL)";

    private static final String TABLE_PRODUCT = "CREATE TABLE " + TABLE_NAME_PRODUCTS + "( " + PRODUCT_ID + " INTENGER PRIMARY KEY AUTOINCREMENT, " +
            NAME+ " VARCHAR NOT NULL, " + PRICE + " INTEGER NOT NULL, FOREIGN KEY(" + TABLE_NAME_CATEGORIES + ") REFERENCES " +
            TABLE_NAME_CATEGORIES + " (" + CATEGORY_ID + ")";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CATEGORY);
        db.execSQL(TABLE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
