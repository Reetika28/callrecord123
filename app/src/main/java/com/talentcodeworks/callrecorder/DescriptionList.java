package com.talentcodeworks.callrecorder;

import android.app.Application;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.api.model.StringList;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapter.CustomAdapter;
import models.DescriptionModels;

/**
 * Created by ahextech on 13/7/17.
 */

public class DescriptionList extends AppCompatActivity {

    private ListView mListView;
    private  DatabaseReference mdatabase;
    private ArrayList<String> mUsernames = new ArrayList<String>();
    Button submit;
    TextView textView;
    public EditText firnumber, phoneNumber, description, complaintName, date, time;
    ListView list;
    CustomAdapter adapter;
    public  DescriptionActivity CustomListView = null;
    public ArrayList<DescriptionModels> CustomListViewValuesArr = new ArrayList<DescriptionModels>();
    Firebase mRootRef;
    HashMap<String,HashMap<String,String>> hashMap = new HashMap<String, HashMap<String, String>>();



    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        firnumber = (EditText) findViewById(R.id.firnumber);
        description = (EditText) findViewById(R.id.description);
        textView = (TextView) findViewById(R.id.showDetails);
        submit = (Button) findViewById(R.id.submit);

//        mListView.invalidateViews();

        mListView = (ListView) findViewById(R.id.list_item);
        mdatabase = FirebaseDatabase.getInstance().getReference().child("users");

        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
        adapter=new CustomAdapter( this, CustomListViewValuesArr);
        //setListData();

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

                    DescriptionModels user = new DescriptionModels(dataHashmap.get("description"), dataHashmap.get("complaintName"),dataHashmap.get("firnumber"),dataHashmap.get("phoneNumber"),dataHashmap.get("date"),dataHashmap.get("time"), dataHashmap.get("reviews"));

                    Log.i("keys",""+keys);
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


        /*DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://callrecorder-master1-17c8a.firebaseio.com/Users/user_01");
        FirebaseListAdapter<String> firebaseListAdapter= new FirebaseListAdapter<String>(

                this,
                String.class,
                android.R.layout.simple_list_item_1,
                databaseReference
        ){
            @Override
            protected void populateView(View v, String model, int position){


                TextView textView = (TextView)v.findViewById(R.id.text1);
                textView.setText(model);
            }
        };

        mListView.setAdapter(firebaseListAdapter);
    }*/

        /*mdatabase = FirebaseDatabase.getInstance().getReference().child("users");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String firnumbe= firnumber.getText().toString().trim();
                String descriptio= description.getText().toString().trim();
                HashMap<String, String> datamap = new HashMap<String, String>();
                datamap.put("firnumber",firnumbe);
                datamap.put("description",descriptio);
                mdatabase.push().setValue(datamap);

            }
        });*/
       /* final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mUsernames);
        mListView.setAdapter(arrayAdapter);*/

        mdatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DescriptionModels value = dataSnapshot.getValue(DescriptionModels.class);

                CustomListViewValuesArr.add(value);
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



    public void setListData()
    {

        for (int i = 0; i < 11; i++) {

            DescriptionModels sched = new DescriptionModels();

            /******* Firstly take data in model object ******/
            sched.setFirnumber("firnumber "+i);
            sched.setComplaintName("complaint_name"+i);
            sched.setPhoneNumber("phone_number"+i);
            sched.setDate("date"+i);
            sched.setTime("time"+i);
            sched.setDescription("description"+i);


            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add( sched );
        }


         adapter.notifyDataSetChanged();
    }

}

