package com.example.parcial2;


import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.parcial2.helpers.FragmentHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    //private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        Toolbar toolbar = findViewById(R.id.toolBar);
        toolbar.inflateMenu(R.menu.menu_toolbar);
        toolbar.setTitle("Parcial 2");

        FloatingActionButton button = findViewById(R.id.callCreateProductButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentHelper.ReplaceFragment(new AddProductFragment(), MainActivity.this);
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ProductListFragment productListFragment = new ProductListFragment();

        AddProductFragment addProductFragment = new AddProductFragment();

        UpdateDeleteProductFragment updateDeleteProductFragment = new UpdateDeleteProductFragment();

        fragmentTransaction.replace(R.id.fragment_container, productListFragment);
        fragmentTransaction.commit();
    }
}