package com.talentcodeworks.callrecorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import adapter.ComplaintAdapter;
import adapter.CustomAdapter;
import models.ComplaintModel;
import models.DescriptionModels;

/**
 * Created by ahextech on 25/7/17.
 */

public class ComplaintInfoList extends AppCompatActivity {

    private ListView mListView;
    private DatabaseReference mdatabase;
    private ArrayList<String> mUsernames = new ArrayList<String>();

    Button moreInfo;

    ComplaintAdapter adapter;
    public  DescriptionActivity CustomListView = null;
    public ArrayList<ComplaintModel> CustomListViewValuesArr = new ArrayList<ComplaintModel>();
    Firebase mRootRef;
    HashMap<String,HashMap<String,String>> hashMap = new HashMap<String, HashMap<String, String>>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        moreInfo=(Button)findViewById(R.id.moreinfo);

       /* moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ComplaintActivity.class);
                startActivity(intent);
            }
        });*/

        mListView = (ListView) findViewById(R.id.list_item);
        mdatabase = FirebaseDatabase.getInstance().getReference().child("users");

        adapter=new ComplaintAdapter( this, CustomListViewValuesArr);

        mRootRef = new Firebase("https://callrecorder-master1-17c8a.firebaseio.com/users");

        com.firebase.client.Query queryRef = mRootRef.orderByChild("date");

        mRootRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {

                CustomListViewValuesArr.clear();
                Log.i("listsss",""+dataSnapshot);
                Log.i("listsss",""+dataSnapshot.getValue());

                hashMap = (HashMap<String, HashMap<String, String>>) dataSnapshot.getValue();

                for (String keys : hashMap.keySet()) {

                    HashMap<String,String> dataHashmap = hashMap.get(keys);

                    //  DescriptionModels sched = new DescriptionModels();
                    //  sched.getKey();

                    /******* Firstly take data in model object ******/

                    ComplaintModel user = new ComplaintModel();
                    user.setName(dataHashmap.get("name"));
                    user.setTime(dataHashmap.get("time"));
                    user.setKey(keys);

                  //  Log.i("keys",""+keys);
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
                adapter.notifyDataSetChanged();
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

        mListView.setAdapter( adapter );

        mdatabase.addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ComplaintModel value = dataSnapshot.getValue(ComplaintModel.class);
                //CustomListViewValuesArr.clear();
               // CustomListViewValuesArr.add(value);
                //  arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    }

