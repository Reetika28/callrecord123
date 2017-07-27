package com.talentcodeworks.callrecorder;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import adapter.CustomAdapter;
import models.DescriptionModels;

/**
 * Created by ahextech on 12/7/17.
 */

public class DescriptionActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Firebase mRootRef;

    public EditText firnumber, phoneNumber, description, complaintName, date, time ;
    Button submit;
    TextView textView;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;
    private ListView mListView;
    private  DatabaseReference mdatabase;
    public  TextView reviews;


    protected  void onCreate(Bundle savedInstanceinstance) {
        super.onCreate(savedInstanceinstance);
        setContentView(R.layout.description);

        firnumber = (EditText) findViewById(R.id.firnumber);
        description = (EditText) findViewById(R.id.description);
        phoneNumber = (EditText)findViewById(R.id.phone_number);
        complaintName = (EditText)findViewById(R.id.complaintname) ;
        date = (EditText)findViewById(R.id.date);
        time = (EditText)findViewById(R.id.time);
        reviews = (TextView) findViewById(R.id.review);
        textView = (TextView) findViewById(R.id.showDetails);
        submit = (Button) findViewById(R.id.submit);


        if( firnumber.getText().toString().length() == 0 )
            firnumber.setError( "Fir number is required!" );

        mRootRef = new Firebase("https://callrecorder-master1-17c8a.firebaseio.com/users");
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");
        mFirebaseInstance.getReference("app_title").setValue("Realtime Database");


        /*mdatabase = FirebaseDatabase.getInstance().getReference().child("users");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String firnumbe= firnumber.getText().toString().trim();
                String descriptio= description.getText().toString().trim();
                HashMap<String, String> datamap = new HashMap<String, String>();
                datamap.put("firnumber",firnumbe);
                datamap.put("description",descriptio);
                mdatabase.setValue(datamap);

            }
        });*/

       /* mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value= dataSnapshot.getValue(String.class);
                String firnumbe= firnumber.getText().toString().trim();
                String descriptio= description.getText().toString().trim();
                HashMap<String, String> datamap = new HashMap<String, String>();
                datamap.put("firnumber",firnumbe);
                datamap.put("description",descriptio);
                mdatabase.push().setValue(datamap);
                textView.setText("firnumber:" +value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

       mRootRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {

                Log.i("valuewsss",""+dataSnapshot);
                Log.i("valuewsss",""+dataSnapshot.getValue());
               // String value= dataSnapshot.getValue(String.class);
                /*for (int i=0;i<value.length(); i++){
                    textView.setText(i);
                }*/


              //  String value= dataSnapshot.getValue();
             //   textView.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        /*mListView = (ListView)findViewById(R.id.list_item);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://callrecorder-master1-17c8a.firebaseio.com/users");
        FirebaseListAdapter<String> firebaseListAdapter= new FirebaseListAdapter<String>(

                this,
                String.class,
                android.R.layout.simple_list_item_1,
                databaseReference
        ){
            @Override
            protected void populateView(View v, String model, int position){

                //TextView textView = (TextView)v.findViewById(R.id.showDetails);
               // textView.setText(model);
            }
        };

        mListView.setAdapter(firebaseListAdapter);
*/


        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "App title updated");

                String appTitle = dataSnapshot.getValue(String.class);

                // update toolbar title
                getSupportActionBar().setTitle(appTitle);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read app title value.", error.toException());
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* String firnumbe = firnumber.getText().toString();
                String descriptio = description.getText().toString();
                String complain = complaint_name.getText().toString();
                String phonenumber = phone_number.getText().toString();
                String dat = date.getText().toString();
                String tim = time.getText().toString();*/

                String descriptio = description.getText().toString();
                String complain = complaintName.getText().toString();
                String firnumbe = firnumber.getText().toString();
                String phonenumber = phoneNumber.getText().toString();
                String dat = date.getText().toString();
                String tim = time.getText().toString();
                String review= "0";

                // Check for already existed userId
                if (TextUtils.isEmpty(userId)) {
                    createUser(descriptio, complain,firnumbe,phonenumber,dat, tim,review);

                } else {
                    updateUser(descriptio, complain,firnumbe,phonenumber,dat, tim, review);
                }
            }
        });

        toggleButton();
    }



    private void toggleButton() {
        if (TextUtils.isEmpty(userId)) {
            submit.setText("Save");
        } else {
            submit.setText("Update");
        }
    }

    private void createUser( String description, String complaintName, String firnumber, String phoneNumber, String date, String time, String reviews ) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }

        DescriptionModels user = new DescriptionModels(description, complaintName,firnumber,phoneNumber,date,time, reviews);

        mFirebaseDatabase.child(userId).setValue(user);

        addUserChangeListener();
    }

    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DescriptionModels user = dataSnapshot.getValue(DescriptionModels.class);

                Log.i("userdata",""+user);
                // Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data is changed!" + user.firnumber + ", " + user.description);

                // Display newly updated name and email
               /* textView.setText(user.firnumber + ", " + user.description);
                // clear edit text
                firnumber.setText("");
                description.setText("");*/

                toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

    private void updateUser( String description, String complaintName, String firnumber, String phoneNumber, String date, String time, String reviews) {
        // updating the user via child nodes
        if (!TextUtils.isEmpty(description))
            mFirebaseDatabase.child(userId).child("description").setValue(description);

        if (!TextUtils.isEmpty(complaintName))
            mFirebaseDatabase.child(userId).child("complaintName").setValue(complaintName);
        if (!TextUtils.isEmpty(firnumber))
            mFirebaseDatabase.child(userId).child("firnumber").setValue(firnumber);
        if (!TextUtils.isEmpty(phoneNumber))
            mFirebaseDatabase.child(userId).child("phoneNumber").setValue(phoneNumber);
        if (!TextUtils.isEmpty(date))
            mFirebaseDatabase.child(userId).child("date").setValue(date);
        if (!TextUtils.isEmpty(time))
            mFirebaseDatabase.child(userId).child("time").setValue(time);
        /*if (!TextUtils.isEmpty(reviews))
            mFirebaseDatabase.child(userId).child("reviews").setValue(reviews);*/
    }

    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(DescriptionActivity.this, MainActivity.class);
        startActivity(intent);
    }
    }

