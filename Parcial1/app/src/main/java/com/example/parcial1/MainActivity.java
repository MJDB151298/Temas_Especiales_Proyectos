package com.example.parcial1;

import android.content.ClipData;
import android.content.res.Configuration;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ComponentFragment componentFragment = new ComponentFragment();
        DefinitionFragment definitionFragment = new DefinitionFragment();
        fragmentTransaction.replace(R.id.fragment_container, componentFragment);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            Bundle bundle = new Bundle();
            bundle.putString("COMPONENT", "Haga click sobre un componente para ver su definicion");
            definitionFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.horizontal_container, definitionFragment);
        }
        fragmentTransaction.commit();

    }
}