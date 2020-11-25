package com.example.parcial2.entitites;

import android.content.Context;
import android.database.Cursor;
import com.example.parcial2.database.DBManagerCategory;
import com.example.parcial2.database.DBManagerProducts;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int id;
    private String name;
    private int price;
    private Category category;

    public Product(int id, String name, int price, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static List<Product> getProducts(Context context){
        DBManagerProducts dbManagerProducts = new DBManagerProducts(context).open();
        DBManagerCategory dbManagerCategory = new DBManagerCategory(context).open();
        List<Product> productList = new ArrayList<>();
        Cursor products = dbManagerProducts.fetch();
        try{
            while(products.moveToNext()){
                String categoryName = dbManagerCategory.fetchByID(products.getInt(3)).getString(0);
                System.out.println(categoryName);
                int categoryId = dbManagerCategory.fetchByID(products.getInt(3)).getInt(1);
                Category category = new Category(categoryId, categoryName);
                Product product = new Product(products.getInt(0), products.getString(1), products.getInt(2),
                        category);
                productList.add(product);
            }
        }finally {
            products.close();
        }
        dbManagerProducts.close();
        dbManagerCategory.close();
        return productList;
    }

    public static boolean isCategoryInProduct(Context context, Category category){
        boolean result = false;
        DBManagerProducts dbManagerProducts = new DBManagerProducts(context).open();
        DBManagerCategory dbManagerCategory = new DBManagerCategory(context).open();
        Cursor products = dbManagerProducts.fetch();
        try{
            while(products.moveToNext()){
                int categoryId = dbManagerCategory.fetchByID(products.getInt(3)).getInt(1);
                if(categoryId == category.getId()){
                    result = true;
                }
            }
        }finally {
            products.close();
        }
        dbManagerProducts.close();
        dbManagerCategory.close();
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
