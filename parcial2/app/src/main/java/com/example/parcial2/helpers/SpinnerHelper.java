package com.example.parcial2.helpers;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.parcial2.R;
import com.example.parcial2.entitites.Category;

import java.util.List;

public class SpinnerHelper {
    public static ArrayAdapter<String> fillCategorySpinner(Spinner spinner, Context context){
        List<String> categoryList = Category.getCategories(context);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context, android.R.layout.simple_spinner_item, categoryList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return adapter;
    }
}
