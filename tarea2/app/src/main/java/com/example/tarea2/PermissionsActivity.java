package com.example.tarea2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import com.example.tarea2.helpers.IntentHelpers;
import com.example.tarea2.helpers.PermissionHelpers;
import com.example.tarea2.helpers.TextViewHelpers;

import java.lang.reflect.Array;
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

        /**Setting the textview events to open stuff and things**/
        int i = 0;
        ArrayList<Intent> intents = IntentHelpers.getPermissionIntents();
        ArrayList<String> manifest_permission = PermissionHelpers.getManifestPermissions();
        for(int textviews_id : IntentHelpers.getPermissionTextViews()){
            int finalI = i;
            findViewById(textviews_id).setOnClickListener(v -> {
                if(ContextCompat.checkSelfPermission(PermissionsActivity.this, manifest_permission.get(finalI)) == PackageManager.PERMISSION_GRANTED)
                    startActivity(intents.get(finalI));
                else
                    Toast.makeText(PermissionsActivity.this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            });
            i++;
        }
    }
}