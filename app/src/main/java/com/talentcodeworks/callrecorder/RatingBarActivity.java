package com.talentcodeworks.callrecorder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import models.DescriptionModels;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by ahextech on 17/7/17.
 */

public class RatingBarActivity extends Activity {
    RatingBar ratingbar1;
    Button button, homebutton;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;
    String reviews;
    Firebase mRootRef;
    TextView ratingtextview;
    HashMap<String,HashMap<String,String>> hashMap = new HashMap<String, HashMap<String, String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_bar);
        addListenerOnButtonClick();

        homebutton = (Button)findViewById(R.id.homebutton);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RatingBarActivity.this, Home.class);
                startActivity(intent);
            }
        });

        userId = getIntent().getStringExtra("key");

        Log.i("useridddddd",""+userId);
        ratingtextview = (TextView)findViewById(R.id.rating_textview);

        mRootRef = new Firebase("https://callrecorder-master1-17c8a.firebaseio.com/users");
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

      //  userId = " -KptxVKcYyF7Nh0ZETX_";
       /* userId = mFirebaseDatabase.push().getKey();

        userId = "-KpFwC407gC5b_pcoXcg";
        mRootRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {

                Log.i("listsss",""+dataSnapshot);
                Log.i("listsss",""+dataSnapshot.getValue());

                hashMap = (HashMap<String, HashMap<String, String>>) dataSnapshot.getValue();

                for (String keys : hashMap.keySet()) {

                    HashMap<String,String> dataHashmap = hashMap.get(keys);

                    DescriptionModels sched = new DescriptionModels();

                    Log.i("keyss",""+keys);
                  //  userId=  sched.getKey();
                    userId=  keys;

                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });*/

    }

    public void addListenerOnButtonClick(){
        ratingbar1=(RatingBar)findViewById(R.id.ratingBar);
        button=(Button)findViewById(R.id.submit);
        //Performing action on Button Click
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                //Getting the rating and displaying it on the toast
                String reviews=String.valueOf(ratingbar1.getRating());
                ratingtextview.setText("You have rated the Product : " + reviews + "/5.");
                 Toast.makeText(getApplicationContext(),"your feedback has been taken successfully",Toast.LENGTH_SHORT).show();

              //  Toast.makeText(getApplicationContext(), reviews, Toast.LENGTH_LONG).show();

                updateUser(reviews);
            }

        });
    }

    public void  updateUser(String reviews){
        if (!TextUtils.isEmpty(reviews))
            mFirebaseDatabase.child(userId).child("reviews").setValue(reviews);
    }

    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(RatingBarActivity.this, ComplaintActivity.class);
        startActivity(intent);
    }
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }*/

}
