package com.example.parcial2.helpers;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.parcial2.ProductListFragment;
import com.example.parcial2.R;

public class FragmentHelper {
    public static void ReplaceFragment(Fragment fragment, FragmentActivity activity){
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragmentManager.findFragmentById(R.id.fragment_container));
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
