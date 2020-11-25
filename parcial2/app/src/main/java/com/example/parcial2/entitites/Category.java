package com.example.parcial2.entitites;

import android.content.Context;
import android.database.Cursor;
import com.example.parcial2.database.DBManagerCategory;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private int id;
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<String> getCategories(Context context){
        DBManagerCategory dbManagerCategory = new DBManagerCategory(context).open();
        List<String> categoryList = new ArrayList<>();
        Cursor categories = dbManagerCategory.fetch();
        try{
            while(categories.moveToNext()){
                System.out.println(categories.getString(0));
                categoryList.add(categories.getString(0));
            }
        } finally {
            categories.close();
        }
        dbManagerCategory.close();
        return categoryList;
    }

    public static Category getCategoryByName(Context context, String name){
        DBManagerCategory dbManagerCategory = new DBManagerCategory(context).open();
        Cursor categories = dbManagerCategory.fetchByName(name);
        Category category = new Category(categories.getInt(1), categories.getString(0));
        dbManagerCategory.close();
        return category;
    }
}
