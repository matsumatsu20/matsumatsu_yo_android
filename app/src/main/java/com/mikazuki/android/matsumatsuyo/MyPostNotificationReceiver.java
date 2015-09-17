package com.mikazuki.android.matsumatsuyo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by matsuMac on 2015/09/13.
 */
public class MyPostNotificationReceiver extends BroadcastReceiver {
    public static final String CONTENT_KEY = "contentText";

    public MyPostNotificationReceiver() {
    }

//    @Override
//    public void onReceive(Context context, Intent intent) {
//        try {
//            System.out.println("aoisdnfagiondfiognfoigiofgiosiorghiostgiosrdiogiorgiorhg");
//            String action = intent.getAction();
//            String channel = intent.getExtras().getString("com.nifty.Channel");
//            JSONObject json = new JSONObject(intent.getExtras().getString("com.nifty.Data"));
//
//            Iterator itr = json.keys();
//            while (itr.hasNext()) {
//                String key = (String) itr.next();
//            }
//        } catch (JSONException e) {
//            // エラー処理
//        }
//    }


    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("aoisdnfagiondfiognfoigiofgiosiorghiostgiosrdiogiorgiorhg");
        Intent displayIntent = new Intent(context, MyDisplayActivity.class);
        String text = intent.getStringExtra(CONTENT_KEY);
        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(text)
                .build();
        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(0, notification);

        Toast.makeText(context, "Notification posted!!", Toast.LENGTH_SHORT).show();
    }
}
