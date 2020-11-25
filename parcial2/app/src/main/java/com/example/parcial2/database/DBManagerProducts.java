package com.example.parcial2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.parcial2.entitites.Product;

public class DBManagerProducts {
    private DatabaseHelper helper;
    private Context context;
    private SQLiteDatabase database;

    public DBManagerProducts(Context context) {
        this.context = context;
    }

    public DBManagerProducts open() throws SQLException {
        helper = new DatabaseHelper(this.context);
        database = helper.getWritableDatabase();
        return this;
    }

    public void close() {
        helper.close();
    }

    public void insert(String name, int price, int category_id) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.NAME, name);
        values.put(DatabaseHelper.PRICE, price);
        values.put("CATEGORY", category_id);
        database.insert(DatabaseHelper.TABLE_NAME_PRODUCTS, null, values);
    }

    public void update(Product product){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.NAME, product.getName());
        values.put(DatabaseHelper.PRICE, product.getPrice());
        values.put("CATEGORY", product.getCategory().getId());
        database.update(DatabaseHelper.TABLE_NAME_PRODUCTS, values, "id = ?", new String[]{Integer.toString(product.getId())});
    }

    public Boolean delete(Integer id) {
        database.delete(DatabaseHelper.TABLE_NAME_PRODUCTS, "id = ?", new String[]{id.toString()});
        return Boolean.TRUE;
    }

    public Cursor fetch() {
        String[] columns = new String[]{DatabaseHelper.PRODUCT_ID, DatabaseHelper.NAME, DatabaseHelper.PRICE, "CATEGORY"};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME_PRODUCTS, columns, null, null, null, null, null);
        //if (cursor != null) {
        //    cursor.moveToFirst();
        //    System.out.println(cursor.getInt(0));
        //}
        return cursor;
    }
}
