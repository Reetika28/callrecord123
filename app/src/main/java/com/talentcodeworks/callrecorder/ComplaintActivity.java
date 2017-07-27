package com.talentcodeworks.callrecorder;

import android.*;
import android.Manifest;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import adapter.ComplaintAdapter;
import adapter.CustomAdapter;
import models.ComplaintModel;
import models.DescriptionModels;

/**
 * Created by ahextech on 25/7/17.
 */

public class ComplaintActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;
    private DevicePolicyManager mDPM;
    private ComponentName mAdminName;
    private String file = "mydata";
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;
    Firebase mRootRef;
    TextView descriptions, name,time, number;
    ImageView call_icon;
    EditText phone_number;
    Button recording, feedback;
    HashMap<String,HashMap<String,String>> hashMap = new HashMap<String, HashMap<String, String>>();
    public ArrayList<ComplaintModel> CustomListViewValuesArr = new ArrayList<ComplaintModel>();
    ComplaintAdapter adapter;
    String description;
    RatingBar ratingBar;
    Button submit;
    TextView recordFiles, record;

    protected  void onCreate(Bundle savedInstanceinstance) {
        super.onCreate(savedInstanceinstance);
        setContentView(R.layout.complaintinfo_item);

      //  setListData();

        userId = getIntent().getStringExtra("key");
        descriptions = (TextView)findViewById(R.id.description);
        time = (TextView)findViewById(R.id.time);
        name = (TextView)findViewById(R.id.name);
        call_icon = (ImageView) findViewById(R.id.call_icon);
       // phone_number = (EditText)findViewById(R.id.contactnumber);
       // recording = (Button)findViewById(R.id.recording);
        number = (TextView) findViewById(R.id.contactnumber);
       // feedback = (Button)findViewById(R.id.feedback);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        submit = (Button)findViewById(R.id.submit1);
        recordFiles = (TextView)findViewById(R.id.recordFiles);
        record = (TextView)findViewById(R.id.file);

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mp=new MediaPlayer();
                Intent intent = new Intent(getApplicationContext(), CallLog.class);
                startActivity(intent);
            }
        });

        recordFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mp=new MediaPlayer();
                Intent intent = new Intent(getApplicationContext(), CallLog.class);
                startActivity(intent);
            }
        });

       /* recording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mp=new MediaPlayer();
                Intent intent = new Intent(getApplicationContext(), CallLog.class);
                startActivity(intent);
            }
        });*/


        mRootRef = new Firebase("https://callrecorder-master1-17c8a.firebaseio.com/users");
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        mRootRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {

                CustomListViewValuesArr.clear();
                Log.i("listsss",""+dataSnapshot);
                Log.i("listsss",""+dataSnapshot.getValue());

                hashMap = (HashMap<String, HashMap<String, String>>) dataSnapshot.getValue();

                for (final String keys : hashMap.keySet()) {

                    final HashMap<String,String> dataHashmap = hashMap.get(keys);

                    //  DescriptionModels sched = new DescriptionModels();
                    //  sched.getKey();

                    /******* Firstly take data in model object ******/

                    ComplaintModel user = new ComplaintModel(dataHashmap.get("time"), dataHashmap.get("name"), dataHashmap.get("description"),dataHashmap.get("reviews"),dataHashmap.get(keys));
                    userId = getIntent().getStringExtra("key");

                    if (userId.equals(keys)) {
                        descriptions.setText(dataHashmap.get("description"));
                        time.setText(dataHashmap.get("time"));
                        name.setText(dataHashmap.get("name"));

                        number.setText(dataHashmap.get("number"));

                        if (dataHashmap.get("reviews").equals("0") || dataHashmap.get("reviews").equals("0.0")) {
                            submit.setVisibility(View.VISIBLE);
                            ratingBar.setVisibility(View.GONE);
                        } else {
                            submit.setVisibility(View.GONE);
                            ratingBar.setVisibility(View.VISIBLE);

                            ratingBar.setRating(Float.valueOf(dataHashmap.get("reviews")));
                        }


                        // holder.submit.setText(list.get(position).getReviews());

                        final boolean clickable = true;

                        final String rating = submit.getText().toString();
                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                //  Log.i("key",""+list.get(position).getKey());

                                Intent intent = new Intent(getApplicationContext(), RatingBarActivity.class);
                                intent.putExtra("key", userId);
                                startActivity(intent);

                            }


                        });
                    }
                    Log.i("keyccc",""+keys);
                   /* sched.setKey(keys);
                    sched.setFirnumber(dataHashmap.get("firnumber"));
                    sched.setComplaintName(dataHashmap.get("complaintName"));
                    sched.setPhoneNumber(dataHashmap.get("phoneNumber"));
                    sched.setDate(dataHashmap.get("date"));
                    sched.setTime(dataHashmap.get("time"));
                    sched.setDescription(dataHashmap.get("description"));
                    sched.setReviews(dataHashmap.get("reviews"));
*/

                    /******** Take Model Object in ArrayList **********/
                    CustomListViewValuesArr.add( user );


                }

                Log.i("sizes",""+CustomListViewValuesArr.size());

               /* String value= dataSnapshot.getValue(String.class);
                for (int i=0;i<value.length(); i++){
                    textView.setText(i);
                }*/


                //  String value= dataSnapshot.getValue();
                //   textView.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        /**************** Create Custom Adapter *********/


         call_icon.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                     ActivityCompat.requestPermissions(ComplaintActivity.this,
                             new String[]{Manifest.permission.CALL_PHONE},
                             1);

                     Log.i("hello", "Hello");

                 } else {

                     Intent callIntent = new Intent(Intent.ACTION_CALL);
                     String number = phone_number.getText().toString();
                     callIntent.setData(Uri.parse("tel:" +number));
                     startActivity(callIntent);
                 }


             }
         });

        try {
            // Initiate DevicePolicyManager.
            mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
            mAdminName = new ComponentName(this, DeviceAdminDemo.class);

            if (!mDPM.isAdminActive(mAdminName)) {
                Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mAdminName);
                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Click on Activate button to secure your application.");
                startActivityForResult(intent, REQUEST_CODE);
            } else {
                // mDPM.lockNow();
                // Intent intent = new Intent(MainActivity.this,
                // TrackDeviceService.class);
                // startService(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (REQUEST_CODE == requestCode) {
            Intent intent = new Intent(ComplaintActivity.this, TService.class);
            startService(intent);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    String number = phone_number.getText().toString();
                    callIntent.setData(Uri.parse("tel:" +number ));
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(callIntent);

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(ComplaintActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }



    /*public void setListData()
    {

        for (int i = 0; i < 11; i++) {

            ComplaintModel sched = new ComplaintModel();

            *//******* Firstly take data in model object ******//*
            sched.setDescription("description "+i);
            sched.setLoadged("loadged"+i);
            sched.setBy("by"+i);
            sched.setDate("date"+i);



            *//******** Take Model Object in ArrayList **********//*
            CustomListViewValuesArr.add( sched );
        }


        adapter.notifyDataSetChanged();
    }
*/

   /* @Override
    public void onBackPressed() {

        finish();

        Intent intent = new Intent(ComplaintActivity.this, RatingBarActivity.class);
      //  intent.putExtra("key",dataHashmap.get(keys));
        userId = getIntent().getStringExtra("key");
        startActivity(intent);
    }
*/
}
