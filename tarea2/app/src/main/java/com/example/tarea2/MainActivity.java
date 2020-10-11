package com.example.tarea2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.tarea2.helpers.SwitchHelpers;
import com.example.tarea2.helpers.TextViewHelpers;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> manifest_permissions = new ArrayList<>(Arrays.asList(Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CONTACTS ));

    private final int PERMISSION_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextViewHelpers.setTextViewsColors(new ArrayList<>(Arrays.asList(((TextView)findViewById(R.id.continue_button)),
                ((TextView)findViewById(R.id.cancel_button)))));

        ArrayList<Switch> switches = new ArrayList<>();
        for(int switch_id : SwitchHelpers.getSwitches())
            switches.add(findViewById(switch_id));
        /**Setting switches events**/
        SwitchHelpers.setSwitchesListener(switches, manifest_permissions,MainActivity.this);
        SwitchHelpers.setSwitchState(switches, manifest_permissions, MainActivity.this);
    }

    public void callPermissionsActivity(){
        Intent intent = new Intent(getBaseContext(), PermissionsActivity.class);
        startActivity(intent);
    }

    public void continueClick(View view){
        int i = 0;
        ArrayList<String> permissions = new ArrayList<>();
        for(int switch_id : SwitchHelpers.getSwitches()){
            boolean permission_granted = ContextCompat.checkSelfPermission(MainActivity.this, manifest_permissions.get(i)) == PackageManager.PERMISSION_GRANTED;
            if(((Switch)findViewById(switch_id)).isChecked() && !permission_granted)
                permissions.add(manifest_permissions.get(i));
            i++;
        }

        if(permissions.size() > 0)
            ActivityCompat.requestPermissions(MainActivity.this, permissions.toArray(new String[0]), PERMISSION_CODE);
        else
            callPermissionsActivity();
    }

    public void cancelClick(View view){
        finishAndRemoveTask();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        callPermissionsActivity();
    }
}