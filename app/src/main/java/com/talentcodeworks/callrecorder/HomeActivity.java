package com.talentcodeworks.callrecorder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahextech on 20/7/17.
 */

public class HomeActivity extends AppCompatActivity {
    ArrayAdapter<String> arrayAdapter;
    ArrayList countrycodesList;
    Spinner zone_spinner,area_spinner,policeStationSpinner;
    ArrayAdapter CityAdapter,AreaAdapter,categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        zone_spinner = (Spinner)findViewById(R.id.zone_spinner);
        area_spinner = (Spinner)findViewById(R.id.area_spinner);
        policeStationSpinner = (Spinner) findViewById(R.id.police_spinner);

        List<String> categories = new ArrayList<String>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        zone_spinner.setAdapter(dataAdapter);
        area_spinner.setAdapter(dataAdapter);
        policeStationSpinner.setAdapter(dataAdapter);

       // AreaIdList = new ArrayList<String>();
       // CityNamesList = new ArrayList<String>();



       // CityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, CityNamesList);
      //  CityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


/*

        area_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                countryCode = (String)countrycodesList.get(position);

                //Log.i("countrycode",""+countryCode);
                checkFieldsForEmptyValues();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
*/


    }

  //  @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
