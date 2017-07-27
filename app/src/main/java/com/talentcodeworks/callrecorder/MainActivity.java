package com.talentcodeworks.callrecorder;

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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText user_name, phone_number;
    ImageView call_icon;
    Button recordFiles;
    Button description, display;

    private static final int REQUEST_CODE = 0;
    private DevicePolicyManager mDPM;
    private ComponentName mAdminName;
    private String file = "mydata";
    TextView tv;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_name = (EditText) findViewById(R.id.user_name);
        phone_number = (EditText) findViewById(R.id.phone_number);
        call_icon = (ImageView) findViewById(R.id.call_icon);
        recordFiles = (Button)findViewById(R.id.recordFiles);
        description = (Button)findViewById(R.id.description);
        display = (Button)findViewById(R.id.display);


        if( phone_number.getText().toString().length() == 0 )
            phone_number.setError( "phone number is required!" );


         description.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getApplicationContext(),ComplaintActivity.class);
                 startActivity(intent);
             }
         });


        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ComplaintInfoList.class);
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



        call_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
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
            Intent intent = new Intent(MainActivity.this, TService.class);
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
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }



}
