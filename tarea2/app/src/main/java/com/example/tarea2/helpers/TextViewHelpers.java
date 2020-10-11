package com.example.tarea2.helpers;

import android.graphics.Color;
import android.widget.TextView;

import java.util.ArrayList;

public class TextViewHelpers {

    /**Se encarga de convertir el color de los TextView a rojo**/
    public static void setTextViewsColors(ArrayList<TextView> textViews){
        for(TextView textView : textViews)
            textView.setTextColor(Color.RED);
    }
}
