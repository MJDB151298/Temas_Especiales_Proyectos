package com.example.parcial2;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ProductListFragment productListFragment = new ProductListFragment();

        AddProductFragment addProductFragment = new AddProductFragment();

        UpdateDeleteProductFragment updateDeleteProductFragment = new UpdateDeleteProductFragment();

        fragmentTransaction.replace(R.id.fragment_container, productListFragment);
        fragmentTransaction.commit();
    }
}