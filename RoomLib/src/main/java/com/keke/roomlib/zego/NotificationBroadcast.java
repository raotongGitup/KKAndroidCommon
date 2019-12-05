package com.keke.roomlib.zego;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationBroadcast extends BroadcastReceiver {

    private static final String TAG = "NotificationBroadcast";
    public static final String CANCEL_NOTICE_TYPE="CANCEL_NOTICE_TYPE";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive: " );
        switch (intent.getIntExtra(CANCEL_NOTICE_TYPE, 0)) {
            case 0:
                // Intent intents = new Intent(context, VoicesBinderForegroundService.class);
                //context.stopService(intents);
                 AgoraManager.getInstance().stopSe();

                break;
        }
    }
}