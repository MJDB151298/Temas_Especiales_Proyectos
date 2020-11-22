package com.example.parcial2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManagerCategory {
    private DatabaseHelper helper;
    private Context context;
    private SQLiteDatabase database;

    public DBManagerCategory(Context context) {
        this.context = context;
    }

    public DBManagerCategory open() throws SQLException {
        helper = new DatabaseHelper(this.context);
        database = helper.getWritableDatabase();
        return this;
    }

    public void close() {
        helper.close();
    }

    public void insert(String name) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.NAME, name);
        database.insert(DatabaseHelper.TABLE_NAME_PRODUCTS, null, values);
    }

    public Cursor fetch() {
        String[] columns = new String[]{DatabaseHelper.NAME, DatabaseHelper.CATEGORY_ID};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME_PRODUCTS, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
