package com.mikazuki.android.matsumatsuyo.ui.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.mikazuki.android.matsumatsuyo.domain.entity.User;
import com.mikazuki.android.matsumatsuyo.domain.repository.BaseCallback;
import com.mikazuki.android.matsumatsuyo.domain.repository.SettingRepository;
import com.mikazuki.android.matsumatsuyo.domain.repository.UserRepository;
import com.mikazuki.android.matsumatsuyo.preference.SharedPreferencesUtil;
import com.mikazuki.android.matsumatsuyo.preference.repository.PreferenceSettingRepository;
import com.mikazuki.android.matsumatsuyo.retrofit.repository.RetrofitUserRepository;

import java.io.IOException;

/**
 * Created by matsuMac on 2015/09/12.
 */
public class RegistrationIntentService  extends IntentService {

    private static final String TAG = "RegIntentService";
    private static final String[] TOPICS = {"global"};
    private UserRepository mUserRepository;
    private SettingRepository mSettingRepository;

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mSettingRepository = new PreferenceSettingRepository(getApplicationContext());

        try {
            synchronized (TAG) {
                InstanceID instanceID = InstanceID.getInstance(this);
                String token = instanceID.getToken("930474852898", GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

                sendRegistrationToServer(intent.getStringExtra("NAME"), token, intent.getStringExtra("PASSWORD"));
                subscribeTopics(token);
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to complete token refresh", e);
            finishService();
        }
    }

    private void sendRegistrationToServer(String name, final String token, String password) {
        Log.i(TAG, "GCM Registration Token: " + token);
        mUserRepository = new RetrofitUserRepository(getApplicationContext());
        mUserRepository.createUser(name, token, password, new BaseCallback<User>() {
            @Override
            public void onResponse(User user) {
                if (user != null) {
                    mSettingRepository.setUserId(user.getId());
                    System.out.println(user.getId());
                    mSettingRepository.setUserName(user.getName());
                    System.out.println(user.getName());
                    mSettingRepository.setToken(user.getToken());
                    System.out.println(user.getToken());
                    mSettingRepository.setGcmId(token);
                    finishService();
                }
            }
        });
    }

    private void subscribeTopics(String token) throws IOException {
        for (String topic : TOPICS) {
            GcmPubSub pubSub = GcmPubSub.getInstance(this);
            pubSub.subscribe(token, "/topics/" + topic, null);
        }
    }

    private void finishService() {
        Intent registrationComplete = new Intent(SharedPreferencesUtil.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }
}


