package com.example.tarea_listview;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listview_container);
        ArrayList<String> data = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , data);
        listView.setAdapter(adapter);
        data.add("Java");data.add("Python");data.add("Go");data.add("Ruby");data.add("PHP");data.add("C++");
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                intent.putExtra("LISTVIEW_ITEM", parent.getAdapter().getItem(position).toString());
                startActivity(intent);
            }
        });



    }
}