package com.mikazuki.android.matsumatsuyo.ui.gcm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;
import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.iid.InstanceIDListenerService;
import com.mikazuki.android.matsumatsuyo.util.Constants;

import java.util.Iterator;

/**
 * Created by matsuMac on 2015/09/12.
 */
public class MyGcmListenerService  extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";

    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    @Override
    public void onMessageReceived(String from, Bundle data) {

        StringBuilder sb = new StringBuilder();
        if (data != null) {
            Iterator<?> it = data.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                Log.v("from GCM", "key: " + key);
                Log.v("from GCM", key + ": " + data.getString(key));
            }
        }

        Intent i = null;
        String message = data.getString("message");

        i = new Intent(Constants.GCM_MESSAGE);
        i.putExtra(Constants.GCM_GET_MESSAGE, message);
        Log.d(TAG, "get message");

        if (i != null) {
            Log.d(TAG, "send Broadcast");
            LocalBroadcastManager.getInstance(this).sendBroadcast(i);
        }
    }

}
