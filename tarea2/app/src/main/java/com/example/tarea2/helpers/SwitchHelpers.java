package com.example.tarea2.helpers;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.widget.Switch;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.example.tarea2.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SwitchHelpers {
    public static ArrayList<Integer> getSwitches(){
        return new ArrayList<Integer>(Arrays.asList(R.id.storage_switch, R.id.location_switch, R.id.camara_switch,
                R.id.phone_switch, R.id.contacts_switch));
    }

    public static void setSwitchesListener(ArrayList<Switch> switches, final ArrayList<String> permissions, final Activity activity){
        int i = 0;
        for(final Switch main_switch : switches){
            int finalI = i;
            main_switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if(!isChecked && ContextCompat.checkSelfPermission(activity, permissions.get(finalI)) == PackageManager.PERMISSION_GRANTED){
                    main_switch.setChecked(true);
                    Toast.makeText(activity, "Permiso ya concedido", Toast.LENGTH_SHORT).show();
                }
            });
            i++;
        }
    }

    public static void setSwitchState(ArrayList<Switch> switches, ArrayList<String> permissions, Activity activity){
        int i = 0;
        for(Switch main_switch : switches){
            if(ContextCompat.checkSelfPermission(activity, permissions.get(i)) == PackageManager.PERMISSION_GRANTED)
                main_switch.setChecked(true);
            else
                main_switch.setChecked(false);
            i++;
        }
    }
}
