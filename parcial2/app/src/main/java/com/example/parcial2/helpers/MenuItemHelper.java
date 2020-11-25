package com.example.parcial2.helpers;

import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import com.example.parcial2.R;

public class MenuItemHelper {
    public static void SwitchMenuItem(FragmentActivity activity){
        Toolbar toolbar = activity.findViewById(R.id.toolBar);
        Menu menu = toolbar.getMenu();
        MenuItem addNewProductMenuButton = menu.findItem(R.id.addNewProductMenuButton);
        addNewProductMenuButton.setVisible(false);
        addNewProductMenuButton.setEnabled(false);
    }
}
