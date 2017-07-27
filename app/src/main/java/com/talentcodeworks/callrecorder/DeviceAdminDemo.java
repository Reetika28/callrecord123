package com.talentcodeworks.callrecorder;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by ahextech on 29/6/17.
 */

public class DeviceAdminDemo extends DeviceAdminReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    public void onEnabled(Context context, Intent intent) {
    };

    public void onDisabled(Context context, Intent intent) {
    };
}
