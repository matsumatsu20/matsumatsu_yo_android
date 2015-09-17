package com.mikazuki.android.matsumatsuyo.preference.repository;

import android.content.Context;
import android.util.Log;

import com.mikazuki.android.matsumatsuyo.domain.repository.SettingRepository;
import com.mikazuki.android.matsumatsuyo.domain.repository.UserRepository;
import com.mikazuki.android.matsumatsuyo.preference.SharedPreferencesUtil;

/**
 * Created by matsuMac on 2015/09/12.
 */
public class PreferenceSettingRepository extends PreferenceBaseRepository implements SettingRepository {

    private String TAG = "PreferenceSettingRepository";
    private UserRepository mUserRepository;

    public PreferenceSettingRepository(Context context) {
        super(context);
    }

    @Override
    public int getUserId() {
        return mSharedPreferences.getInt(SharedPreferencesUtil.USER_ID, -1);
    }

    @Override
    public void setUserId(int id) {
        if (id < 0) {
            mSharedPreferences.edit().remove(SharedPreferencesUtil.USER_ID).apply();
        } else {
            mSharedPreferences.edit().putInt(SharedPreferencesUtil.USER_ID, id).apply();
        }
    }

    @Override
    public String getUserName() {
        return mSharedPreferences.getString(SharedPreferencesUtil.USER_NAME, "");
    }

    @Override
    public void setUserName(String name) {
        if (name == null || "".equals(name)) {
            mSharedPreferences.edit().remove(SharedPreferencesUtil.USER_NAME).apply();
        } else {
            mSharedPreferences.edit().putString(SharedPreferencesUtil.USER_NAME, name).apply();
        }

    }

    @Override
    public int getOpponentId() {
        return mSharedPreferences.getInt(SharedPreferencesUtil.OPPONENT_ID, -1);
    }

    @Override
    public void setOpponentId(int id) {
        if (id < 0) {
            mSharedPreferences.edit().remove(SharedPreferencesUtil.OPPONENT_ID).apply();
        } else {
            mSharedPreferences.edit().putInt(SharedPreferencesUtil.OPPONENT_ID, id).apply();
        }
    }

    @Override
    public String getOpponentName() {
        return mSharedPreferences.getString(SharedPreferencesUtil.OPPONENT_NAME, "");
    }

    @Override
    public void setOpponentName(String name) {
        if (name == null || "".equals(name)) {
            mSharedPreferences.edit().remove(SharedPreferencesUtil.OPPONENT_NAME).apply();
        } else {
            mSharedPreferences.edit().putString(SharedPreferencesUtil.OPPONENT_NAME, name).apply();
        }
    }

    @Override
    public String getToken() {
        Log.d(TAG, mSharedPreferences.getString(SharedPreferencesUtil.REGISTRATION_TOKEN, ""));
        return mSharedPreferences.getString(SharedPreferencesUtil.REGISTRATION_TOKEN, "");
    }

    @Override
    public void setToken(String token) {
        if (token == null || "".equals(token)) {
            mSharedPreferences.edit().remove(SharedPreferencesUtil.REGISTRATION_TOKEN).apply();
        } else {
            mSharedPreferences.edit().putString(SharedPreferencesUtil.REGISTRATION_TOKEN, token).apply();
        }
    }

    @Override
    public String getGcmId() {
        Log.d(TAG, mSharedPreferences.getString(SharedPreferencesUtil.GCM_ID, ""));
        return mSharedPreferences.getString(SharedPreferencesUtil.GCM_ID, "");
    }

    @Override
    public void setGcmId(String token) {
        if (token == null || "".equals(token)) {
            mSharedPreferences.edit().remove(SharedPreferencesUtil.GCM_ID).apply();
        } else {
            mSharedPreferences.edit().putString(SharedPreferencesUtil.GCM_ID, token).apply();
        }
    }

    @Override
    public boolean isRegistered() {
        return !"".equals(getToken());
    }

    @Override
    public boolean isLinked() {
        return getOpponentId() > 0;
    }


    @Override
    public void clear() {
        mSharedPreferences.edit().clear().commit();
    }

}
