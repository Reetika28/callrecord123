package com.talentcodeworks.callrecorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import models.ComplaintModel;

/**
 * Created by ahextech on 25/7/17.
 */

public class Home  extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Firebase mRootRef;

    public EditText name,subject, description,time,date, number;
    public  Button submit, show, clear;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;
    private ListView mListView;
    private  DatabaseReference mdatabase;
    public  TextView reviews;
    public EditText CurrentDate ,currentTime;

    protected void onCreate(Bundle savedInstanceinstance) {
        super.onCreate(savedInstanceinstance);
        setContentView(R.layout.home);


        number=(EditText)findViewById(R.id.contactNumber);
        subject=(EditText)findViewById(R.id.subject);
        name = (EditText)findViewById(R.id.by);
        description =(EditText)findViewById(R.id.description);
        submit = (Button)findViewById(R.id.submit);
        show = (Button)findViewById(R.id.show);
        reviews = (TextView) findViewById(R.id.review);
        CurrentDate = (EditText) findViewById(R.id.currentdate);
        currentTime = (EditText) findViewById(R.id.timetext);
        clear = (Button)findViewById(R.id.buttonclear);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,Home.class);
                startActivity(intent);
                finish();
            }
        });

     /*   SimpleDateFormat sdf = new SimpleDateFormat("EEE d, MMM");
        String current_date = sdf.format(new Date());

        CurrentDate.setText(current_date);

        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm a");
        String current_time = sdf1.format(new Date());
        currentTime.setText(current_time);
*/
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ComplaintInfoList.class);
                startActivity(intent);
            }
        });

        mRootRef = new Firebase("https://callrecorder-master1-17c8a.firebaseio.com/users");
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");
        mFirebaseInstance.getReference("app_title").setValue("Realtime Database");

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

                // String descriptio = description.getText().toString();
                String nam = name.getText().toString();
                String byy = currentTime.getText().toString();
                String loadge = subject.getText().toString();
                String descriptio = description.getText().toString();
                String dat = CurrentDate.getText().toString();
                String review= "0";
               // String numbe = number.getText().toString();


                // Check for already existed userId
                if (TextUtils.isEmpty(userId)) {
                    createUser(nam,loadge, byy,descriptio,dat,review );

                } else {
                    updateUser(nam,loadge, byy ,descriptio,dat,review);
                }
            }
        });

        toggleButton();

    }

    private void toggleButton() {
        if (TextUtils.isEmpty(userId)) {
            submit.setText("Save records");
        } else {
            submit.setText("Update");
        }
    }


    private void createUser(String name, String subject, String time, String description, String date, String reviews) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }

        ComplaintModel user = new ComplaintModel(name,subject,time,description,date,reviews);

        mFirebaseDatabase.child(userId).setValue(user);

        addUserChangeListener();
    }

    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ComplaintModel user = dataSnapshot.getValue(ComplaintModel.class);

                Log.i("userdata",""+user);
                // Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data is changed!" + user.loadged + ", " + user.by);

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

    private void updateUser( String name, String subject, String time, String description, String date, String reviews) {
        // updating the user via child nodes
        if (!TextUtils.isEmpty(subject))
            mFirebaseDatabase.child(userId).child("subject").setValue(subject);

        if (!TextUtils.isEmpty(time))
            mFirebaseDatabase.child(userId).child("time").setValue(time);

        if (!TextUtils.isEmpty(name))
            mFirebaseDatabase.child(userId).child("name").setValue(name);

        if (!TextUtils.isEmpty(description))
            mFirebaseDatabase.child(userId).child("description").setValue(description);
        if (!TextUtils.isEmpty(date))
            mFirebaseDatabase.child(userId).child("date").setValue(date);
        /*if (!TextUtils.isEmpty(number))
            mFirebaseDatabase.child(userId).child("number").setValue(number);*/
    }
}
