package com.talentcodeworks.callrecorder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by ahextech on 29/6/17.
 */

public class TService extends Service{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    /*MediaRecorder recorder;
    File audiofile;

    Context context;
    private Handler handler;
    Timer timer;
    Boolean offHook = false, ringing = false;
    Toast toast;
    Boolean isOffHook = false;
    private boolean recordstarted = false;

    private static final String ACTION_IN = "android.intent.action.PHONE_STATE";
    private static final String ACTION_OUT = "android.intent.action.NEW_OUTGOING_CALL";
    private CallBr br_call;




    @Override
    public void onDestroy() {
        Log.d("service", "destroy");

        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
         final String terminate =(String)
        intent.getExtras().get("terminate");//
         intent.getStringExtra("terminate");
         Log.d("TAG", "service started");
        //
        TelephonyManager telephony = (TelephonyManager)
         getSystemService(Context.TELEPHONY_SERVICE); // TelephonyManager
        // // object
         //CustomPhoneStateListener customPhoneListener = new
        // CustomPhoneStateListener();
        // telephony.listen(customPhoneListener,
         //PhoneStateListener.LISTEN_CALL_STATE);
         context = getApplicationContext();

        final IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_OUT);
        filter.addAction(ACTION_IN);
        this.br_call = new CallBr();
        this.registerReceiver(this.br_call, filter);

        // if(terminate != null) {
        // stopSelf();
        // }
        return START_NOT_STICKY;
    }

    public class CallBr extends BroadcastReceiver {
        Bundle bundle;
        String state;
        String inCall, outCall;
        public boolean wasRinging = false;

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION_IN)) {
                if ((bundle = intent.getExtras()) != null) {


                    state = bundle.getString(TelephonyManager.EXTRA_STATE);

                    Log.i("state",""+state);
                    if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                        inCall = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                        wasRinging = true;
                        Toast.makeText(context, "IN : " + inCall, Toast.LENGTH_LONG).show();
                    } else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {

                        Log.i("is conversation started","yes");
                        if (wasRinging == true) {

                            Toast.makeText(context, "ANSWERED", Toast.LENGTH_LONG).show();

                            String out = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss").format(new Date());
                            File sampleDir = new File(Environment.getExternalStorageDirectory(), "/TestRecordingDasa1");
                            if (!sampleDir.exists()) {
                                sampleDir.mkdirs();
                            }
                            String file_name = "Record";
                            try {
                                audiofile = File.createTempFile(file_name, ".amr", sampleDir);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            String path = Environment.getExternalStorageDirectory().getAbsolutePath();

                            recorder = new MediaRecorder();
                        *//* recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);

                            recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION);*//*
                            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                          //  recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
                            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                            recorder.setOutputFile(audiofile.getAbsolutePath());
                            try {
                                recorder.prepare();
                            } catch (IllegalStateException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            recorder.start();
                            recordstarted = true;
                        }
                    } else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                        wasRinging = false;
                        Toast.makeText(context, "REJECT || DISCO", Toast.LENGTH_LONG).show();
                        if (recordstarted) {
                            recorder.stop();
                            recordstarted = false;
                        }
                    }
                }
            } else if (intent.getAction().equals(ACTION_OUT)) {
                if ((bundle = intent.getExtras()) != null) {
                    outCall = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
                    Toast.makeText(context, "OUT : " + outCall, Toast.LENGTH_LONG).show();
                }
            }
        }
    }*/
}
