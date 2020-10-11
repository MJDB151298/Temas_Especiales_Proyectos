package com.example.tarea2.helpers;

import android.content.Intent;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import com.example.tarea2.R;

import java.util.ArrayList;
import java.util.Arrays;

public class IntentHelpers {

    public static ArrayList<Integer> getPermissionTextViews(){
        return new ArrayList<Integer>(Arrays.asList(R.id.storage_textview, R.id.location_textview, R.id.camara_textview,
                R.id.phone_textview, R.id.contacts_textview));
    }

    public static ArrayList<Intent> getPermissionIntents(){
        Intent storageIntent = new Intent(Intent.ACTION_VIEW).setType("image/*").setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Intent locationIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Intent phoneIntent = new Intent(Intent.ACTION_CALL_BUTTON);
        Intent contactsIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        return new ArrayList<Intent>(Arrays.asList(storageIntent, locationIntent, cameraIntent, phoneIntent, contactsIntent));
    }
}
