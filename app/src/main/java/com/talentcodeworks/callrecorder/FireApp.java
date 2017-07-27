package com.talentcodeworks.callrecorder;

import android.app.Application;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ahextech on 13/7/17.
 */

public class FireApp extends Application {

    public  void onCreate(){
        super.onCreate();
       // Firebase.setAndroidContext(this);
       Firebase.setAndroidContext(this);
       // FirebaseDatabase.getInstance().setPersistenceEnabled(true);

       if (!com.google.firebase.FirebaseApp.getApps(this).isEmpty()){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}
