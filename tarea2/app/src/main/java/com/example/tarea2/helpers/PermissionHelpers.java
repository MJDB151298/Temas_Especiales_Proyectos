package com.example.tarea2.helpers;

import android.Manifest;

import java.util.ArrayList;
import java.util.Arrays;

public class PermissionHelpers {
    public static ArrayList<String> getManifestPermissions(){
        return new ArrayList<>(Arrays.asList(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_CONTACTS ));
    }
}
