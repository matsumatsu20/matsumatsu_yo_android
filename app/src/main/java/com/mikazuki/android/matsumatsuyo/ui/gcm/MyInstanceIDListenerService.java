package com.mikazuki.android.matsumatsuyo.ui.gcm;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.iid.InstanceIDListenerService;
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
public class MyInstanceIDListenerService extends InstanceIDListenerService {

    private static final String TAG = "MyInstanceIDLS";
    private static final String[] TOPICS = {"global"};
    private UserRepository mUserRepository;
    private SettingRepository mSettingRepository;

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. This call is initiated by the
     * InstanceID provider.
     */
    @Override
    public void onTokenRefresh() {
        Log.i(TAG, "tokenがリフレッシュされそうだよ！！！！！！");
        // Fetch updated Instance ID token and notify our app's server of any changes (if applicable).
        Intent intent = new Intent(this, RegistrationIntentService.class);

        mSettingRepository = new PreferenceSettingRepository(getApplicationContext());

        try {
            synchronized (TAG) {
                InstanceID instanceID = InstanceID.getInstance(this);
                String token = instanceID.getToken("930474852898", GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

                Log.i(TAG, "新しいtokenは" + token);

                sendUpdateTokenToServer(token);
                subscribeTopics(token);
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to complete token refresh", e);
            finishService();
        }
    }

    private void sendUpdateTokenToServer(final String token) {
        Log.i(TAG, "GCM Registration Token: " + token);
        int id = mSettingRepository.getUserId();
        mUserRepository = new RetrofitUserRepository(getApplicationContext());
        mUserRepository.updateToken(id, token, new BaseCallback<User>() {
            @Override
            public void onResponse(User user) {
                if (user != null) {
                    mSettingRepository.setToken(token);
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
