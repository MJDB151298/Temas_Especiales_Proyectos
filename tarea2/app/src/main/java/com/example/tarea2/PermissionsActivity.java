package com.example.tarea2;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.tarea2.helpers.TextViewHelpers;

import java.util.ArrayList;
import java.util.Arrays;

public class PermissionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        TextViewHelpers.setTextViewsColors( new ArrayList<TextView>(Arrays.asList(  ((TextView)findViewById(R.id.storage_textview)),
                ((TextView)findViewById(R.id.location_textview)), ((TextView)findViewById(R.id.camara_textview)),
                ((TextView)findViewById(R.id.phone_textview)), ((TextView)findViewById(R.id.contacts_textview)) )));
    }
}